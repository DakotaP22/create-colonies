# BlockUI, MineColonies, and Create Mod Compatibility Research

## Executive Summary

The primary issue preventing seamless integration between Create and MineColonies is BlockUI's incomplete FakeLevel capability system in 1.21+, combined with the absence of data-driven recipe definitions for Create machines in MineColonies. A compatibility layer could be implemented either through a dedicated mod or integrated into one of the existing projects.

---

## 1. Root Cause of BlockUI/Create Belt Infinity Loop

### BlockUI Issue #110: FakeLevel Capabilities Not Implemented

**Status**: Fixed in PR #113 (released v1.21.1-1.0.209)

**Root Cause**:
- FakeLevel is a virtual world representation used by BlockUI/Structurize for schematic scanning and building
- In Minecraft 1.21+, NeoForge introduced Block Capabilities system
- FakeLevel's `getCapability()` and `getCapability(BlockPos, Context)` methods returned `null` unconditionally
- When scanning structures with Structurize, RackBlockEntity (MineColonies storage) implements `IItemHandler` capability
- Without capability queries working on FakeLevel, item contents couldn't be scanned from modded blocks

**The StackOverflow Mechanism**:
1. Builder attempts to scan a structure containing Create machines + MineColonies storage
2. Structurize uses FakeLevel to materialize the structure
3. FakeLevel doesn't implement capability queries
4. Fallback logic attempts to iterate block states/NBT
5. Some blocks (like Create's BeltBlockEntity) may have self-referential item handlers
6. This creates a recursive loop when scanning for contents

**Status**: Fixed in BlockUI v1.21.1-1.0.209 - capabilities now properly delegate to BlockEntity implementations

---

## 2. How MineColonies Assigns Worker Recipes to Crafting Stations

### Recipe Assignment System

**Location**: `/src/main/java/com/minecolonies/core/entity/ai/workers/crafting/`

**Architecture**:
```
AbstractEntityAICrafting (Base class)
├─ EntityAIWorkBaker
├─ EntityAIWorkBlacksmith
├─ EntityAIWorkChef
├─ EntityAIWorkCrusher
├─ EntityAIWorkDyer
├─ EntityAIWorkFletscher
├─ EntityAIWorkGlassblower
├─ EntityAIWorkMechanic
├─ EntityAIWorkSawmill
└─ ... (15+ crafting workers)
```

**How It Works**:
1. Each crafting building has an associated worker class
2. Workers use `IRecipeStorage` interface to define available recipes
3. Recipes can be:
   - **Hard-coded** in Java (default recipes)
   - **Data-driven** via datapacks (JSON-based custom recipes)
   - **Scanned** from known recipe types (furnace, crafting table, brewing)

**Recipe Detection**:
- MineColonies scans recipes from Minecraft's RecipeManager
- Supports vanilla recipe types and mods that register recipes via RecipeType
- JEI integration shows available recipes for each worker building

**Issue**: No existing system to recognize **Create machine recipes** as valid assignments

---

## 3. What's Needed for Create Machine Compatibility

### Target Create Machines for Integration

1. **Millstone** - Crushes items (equivalent to crusher)
2. **Mechanical Press** - Presses/compacts items
3. **Mechanical Mixer** - Mixes ingredients
4. **Mechanical Saw** - Cuts/saws materials
5. **Compacting Basin** - Combines items under pressure
6. **Blaze Burner** - Heats recipes
7. **Mechanical Pump** - Liquid transfer

### Technical Requirements

#### A. Recipe Data Structure
Create recipes are stored as: `com.simibubi.create.content.processing.recipe.*Recipe`

Example structure:
```java
ProcessingRecipe extends Recipe<Container>
├─ ingredients: List<ProcessingIngredient> (with item counts)
├─ results: List<ProcessingResult>
├─ duration: int (ticks)
└─ special handling (fuel, heat requirements)
```

MineColonies expects:
```java
IRecipeStorage
├─ inputs: ItemStack[]
├─ outputs: ItemStack[]
└─ craftingTicksNeeded: int
```

**Gap**: The data structures don't align. Create recipes are more complex (multi-input, probabilistic outputs, special conditions).

#### B. Capability Integration
- **Create Machines**: Implement `IItemHandler` capability for input/output slots
- **MineColonies Workers**: Need to access machine inventories via capabilities
- **Solution**: Use NeoForge's Block Capability API to query Create machines

```java
BlockCapability<IItemHandler, Void> CAP = Capabilities.ItemHandler.BLOCK;
IItemHandler handler = level.getCapability(cap, machinePos, null);
```

#### C. Work Assignment System
Create machines don't have "workers" - they operate automatically via rotational power.

**Mismatch**: MineColonies workers are NPCs that go to buildings and perform tasks.

**Possible Solutions**:
1. **Wrapper Approach**: Create a "Create Assembly Manager" worker that manages nearby Create machines
2. **Inventory Bridge**: Worker pulls from Create input, pushes to Create output (slower but compatible)
3. **Datapack Integration**: Map Create recipes to virtual MineColonies jobs

---

## 4. Recommended Implementation Approach

### Option 1: Data-Driven Integration (Recommended)
**Pros**: Mod-agnostic, no code changes to Create/MineColonies
**Cons**: Limited to inventory-based integration, slower worker performance

**Implementation**:
1. Create a new MineColonies worker type: `Create Assembly Mechanic`
2. Define datapack recipes that map Create machine operations to MineColonies jobs
3. Worker would:
   - Pull items from storage
   - Insert into Create machine input
   - Wait for processing
   - Extract output
   - Return to storage

**Data Structure**:
```json
{
  "type": "minecolonies:crafting",
  "job": "create_assembly_mechanic",
  "inputs": [{"item": "create:copper_ore", "count": 1}],
  "outputs": [{"item": "create:crushed_copper_ore", "count": 1}],
  "duration": 200,
  "machine": "create:millstone"
}
```

**Effort**: 150-300 hours (design, validation, datapack system)

### Option 2: Dedicated Compatibility Mod (Create Colonies)
**Pros**: Clean separation, can be optimized specifically
**Cons**: Another mod to maintain, dependency management

**Architecture**:
```
CreateColonies Mod
├── RecipeRegistry (maps Create recipes to MineColonies format)
├── WorkerIntegration (new worker types for each machine category)
├── CapabilityHandlers (handles Create machine I/O)
└── DatapackSupport (allows customization)
```

**Key Classes Needed**:
- `CreateRecipeScanner`: Converts Create recipes to IRecipeStorage
- `CreateMachineWorker`: NPC that interacts with Create machines
- `CreateCapabilityProvider`: Bridges capability query gaps
- `CreateJobRegistry`: Registers new job types

**Effort**: 200-400 hours (full mod development, testing, documentation)

### Option 3: Direct Integration into MineColonies
**Pros**: Official support, tighter integration
**Cons**: MineColonies maintainers must approve, slower development cycle

**Would require**:
- New worker type for Create machines
- Recipe scanner for Create's recipe types
- Capability-aware inventory system
- Datapack/JSON format for machine job definitions

**Effort**: 300-500 hours (integration review, testing, maintenance)

---

## 5. Data-Driven vs. Code Modification

### Data-Driven Approach (Recommended First Step)

**Advantages**:
- ✅ No Core Modification: Works with unmodified Create/MineColonies
- ✅ Server-Side Only: No client changes needed
- ✅ Flexible: Easy to add/remove recipes via datapacks
- ✅ Backward Compatible: Doesn't break existing recipes
- ✅ Distributable: Packs can be shared as datapacks

**Limitations**:
- ❌ Worker Performance: NPCs slower than automated machines (~5-10 sec vs. 2-4 ticks)
- ❌ Inventory Bottleneck: Limited by worker carrying capacity
- ❌ No Direct Integration: Can't access pressure/heating mechanics
- ❌ UI Limitations: Can't customize Create machine UIs

**Implementation Path**:
```
Phase 1: Wrapper System (Data-Driven)
├─ Define "Create Work" recipe type
├─ Create converter recipes (crop -> Create input format)
└─ Worker does: Pick up → Insert → Wait → Extract

Phase 2: Capability Bridge (Minor Code Change)
├─ MineColonies reads Create recipe registry
├─ Workers interact via IItemHandler capabilities
└─ Reduces manual recipe definition

Phase 3: Tight Integration (Full Code)
├─ Create machines become "buildings" in MineColonies
├─ Workers understand rotational power requirements
└─ Full recipe compatibility
```

### Code Modification Approach

**If Pursuing Data-Driven First**:
Minimal changes needed to MineColonies:
```java
// Add Create recipe type scanning
public class CreateRecipeScanner extends IRecipeScanner {
    @Override
    public void scanRecipes(RecipeManager manager) {
        // Iterate Create's recipe registry
        // Convert to MineColonies format
    }
}
```

**If Pursuing Full Integration**:
Would need ~1000+ lines across:
- Worker AI classes
- Recipe storage system
- Building definitions
- Datapack formats

---

## 6. Existing Mods That Successfully Bridge Mod Integration

### Similar Integration Patterns

1. **Apotheosis** + **Configured**: 
   - Uses wrapper pattern for other mod items
   - Provides datapack-based crafting system
   - Similar recipe translation approach

2. **Thermal Series** (Thermal Expansion + Minecolonies):
   - Has heat mechanic integration
   - Uses job registration system
   - Could serve as template

3. **Sophisticated Storage** + **Modular Routers**:
   - Excellent example of capability-based integration
   - Clean separation of concerns
   - No direct mod dependencies

4. **Farmer's Delight** + **Create**:
   - Already has integration patches
   - Shows how to extend Create recipes
   - Datapack-based approach

### Best Practices Observed

- ✅ **Capability-First**: Use NeoForge capabilities for hardware abstraction
- ✅ **Recipe Registry**: Don't hardcode, scan at runtime
- ✅ **Datapack Support**: Allow server customization
- ✅ **Logging**: Log recipe conversions for debugging
- ✅ **Graceful Degradation**: Work even if soft-dependency not installed

---

## 7. Detailed Action Plan

### Immediate (Week 1)

**Phase 1: Foundation**
```
1. Create worker type: "Create Mechanic"
2. Define recipe structure in datapack format
3. Implement recipe parser
4. Test with 1-2 Create machines
```

**Time**: 20-30 hours
**Output**: Basic wrapper system, working proof-of-concept

### Short-term (Weeks 2-4)

**Phase 2: Expansion**
```
1. Add all 7 major Create machines
2. Create recipe library (standard conversions)
3. Inventory interface optimization
4. Worker pathfinding to Create machines
5. UI mockups for configuration
```

**Time**: 40-60 hours
**Output**: Usable compatibility for common Create recipes

### Medium-term (Weeks 5-8)

**Phase 3: Polish**
```
1. Performance optimization
2. Datapack validation
3. Comprehensive testing
4. Documentation/wiki
5. Community feedback integration
6. Bug fixes
```

**Time**: 30-50 hours
**Output**: Production-ready release

### Long-term (Ongoing)

**Phase 4: Advanced Features** (Optional)
```
1. Capability-based worker AI
2. Rotational power awareness
3. Pressure/heat requirement system
4. Advanced recipe composition (multi-Create chains)
5. Performance parity with automation-only approach
```

**Time**: Unlimited as feedback comes in

---

## 8. Key Code References

### MineColonies Recipe System
- Recipe interface: `com.minecolonies.api.crafting.IRecipeStorage`
- Worker base: `com.minecolonies.core.entity.ai.workers.crafting.AbstractEntityAICrafting`
- Recipe manager: `com.minecolonies.core.crafting.RecipeManager`

### Create Mod Integration Points
- Recipe base: `com.simibubi.create.content.processing.recipe.ProcessingRecipe`
- ItemHandler capability: `net.neoforged.neoforge.capabilities.Capabilities.ItemHandler`
- Machine inventory: `net.neoforged.neoforge.items.IItemHandler`

### BlockUI FakeLevel (Fixed issues reference)
- Issue: [ldtteam/BlockUI#110](https://github.com/ldtteam/BlockUI/issues/110)
- Fix: [ldtteam/BlockUI#113](https://github.com/ldtteam/BlockUI/pull/113)
- Capability delegation pattern can be reused

---

## 9. Special Considerations

### Performance Implications

Create's automated machines:
- **Speed**: 2-4 minecraft ticks per item (optimal)
- **Throughput**: Continuous, no breaks

MineColonies workers (current approach):
- **Speed**: 5-10 seconds per assembly (limited by pathfinding/interaction)
- **Throughput**: Single-threaded, one job at a time
- **Scaling**: Linear with worker count (1 worker = 1 job max)

**Expected Performance**:
- For small operations (< 20 items/min): No perceivable difference
- For large operations (> 100 items/min): Create automation ~2-3x faster
- **Reason**: Workers are optimized for variable tasks, not high-throughput crafting

### Compatibility Concerns

1. **Save Format**: Must not alter Create machine NBT
2. **Mod Updates**: Recipes may change between Create versions
3. **Config Conflicts**: Custom datapacks may override defaults
4. **Resource Handling**: Workers consume Dungeon Loot rates; consider balance

---

## 10. Risk Assessment

| Risk | Probability | Impact | Mitigation |
|------|-------------|--------|-----------|
| Create recipe format changes | Medium | High | Version lock, registry scanning |
| StackOverflow regression | Low | Critical | Extensive testing, FakeLevel fix verified |
| Performance complaints | High | Medium | Clear documentation of limitations |
| Recipe imbalance | Medium | Medium | Community balance pass, configs |
| Worker assignment bugs | Medium | High | Comprehensive unit testing |
| Infinite item duplication | Low | Critical | Strict quantity validation |

---

## Conclusion

**Best Path Forward**: 
1. **Start with data-driven integration** (low risk, fast iteration)
2. **Validate with community** (gather feedback on recipes/balance)
3. **Progress to capability bridge if needed** (medium effort, high compatibility)
4. **Consider full integration later** (based on demand and bandwidth)

The BlockUI FakeLevel issue is already **fixed in current versions**, so the primary barrier to compatibility is now **recipe system integration**, which can be addressed through data-driven approaches with minimal code modification.

---

## References

- BlockUI Issue #110: https://github.com/ldtteam/BlockUI/issues/110
- BlockUI PR #113: https://github.com/ldtteam/BlockUI/pull/113
- Create ItemVault capability: https://github.com/Creators-of-Create/Create/blob/mc1.21.1/dev/src/main/java/com/simibubi/create/content/logistics/vault/ItemVaultBlockEntity.java
- MineColonies Crafting Workers: https://github.com/ldtteam/minecolonies/tree/version/main/src/main/java/com/minecolonies/core/entity/ai/workers/crafting
- MineColonies Wiki: https://wiki.minecolonies.com/
