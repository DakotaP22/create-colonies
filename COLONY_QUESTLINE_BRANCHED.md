# Create Mod Questline × Minecolonies Integration

Worker progression and research integration with **4 specialization branches** + convergence at Brass Age.

---

## Progression Overview

```
                    CHAPTER 1: Rotational Foundations (REQUIRED)
                            ↓
        ┌───────────────────┬───────────────┬───────────────┬─────────────────┐
        ↓                   ↓               ↓               ↓                 ↓
    FARMING            LOGGING         ORE PROCESSING    WORKSHOP      (OPTIONAL PATHS)
    BRANCH             BRANCH          BRANCH            BRANCH
    Ch 2-3             Ch 2-3          Ch 2-3            Ch 2-3            
        ↓                   ↓               ↓               ↓
    CORE PATH          CORE PATH       CORE PATH         CORE PATH
    (Millstone,        (Mech Saw)      (Grinding/        (Mechanical
     Fan Wash)                         Crushing)         Crafters)
        ↓                   ↓               ↓               ↓
        └───────────────────┴───────────────┴───────────────┴─────────────────┘
                            ↓   
                CHAPTER 5: Bridge to Brass (REQUIRED)
                            ↓
                    BRASS AGE (Unified)
                    Ch 6-11
```

---

## 🏡 Colony Foundation (Pre-Research)

Before any research can begin, the player must establish a functional Level 1 colony. Workers handle all construction; the player supplies resources and assigns workers.

**Required buildings (Builder constructs all):**
- **Town Hall** — colony anchor; governs citizen cap and research access
- **Builder's Hut** — required for all construction orders
- **Residence** — housing for workers
- **Warehouse** — central item storage
- **Supply structures** — food, basic materials (e.g. Cook's Hut, Forester's Hut)
- **Courier's Hut** — coordinates resource flow between buildings; essential once Create products enter the supply chain

*Once these are stable and staffed, the player has access to the Town Hall research tree and can begin Chapter 1.*

---

## ⚙️ ANDESITE AGE

### Chapter 1 — Rotational Foundations (Tier 1-2) **[REQUIRED]**

**Research Path:** `andesite-age/ch1-*` (2 required nodes + 1 optional)

All players must complete this chapter to access any specialization branch.  
*Workers handle all construction and crafting — the player manages research and resource supply only.*

> **Note on building vs. research flow:** Some research nodes unlock the ability to build or upgrade a structure; some building upgrades unlock the next research tier. Both directions are used throughout the questline.

**Workers involved:**
- **Blacksmith** (Lv1): Added during Ch1 — produces alloy, shafts, and cogwheels once researched
- **Forester** (Lv1): Provides stripped logs and wood materials
- **Carpenter** (Lv1): Constructs wood-based components and buildings
- **Builder**: Constructs and upgrades the Power Station

**Research Order:**

1. **[Research: Colony Smithing]** `andesite-age/ch1-colony-smithing`  
   - *Requires:* Blacksmith Lv1 (add Blacksmith's Hut to colony first)  
   - *Consume:* 8x Andesite, 4x Iron Nuggets  
   - *Unlocks:* Andesite Alloy recipe *(Blacksmith produces)*

2. **[Research: Rotational Power]** `andesite-age/ch1-rotational-power`  
   - *Requires:* Colony Smithing complete, Blacksmith Lv1, Carpenter Lv1  
   - *Consume:* 4x Andesite Alloy, 8x Oak Planks  
   - *Unlocks:* Shaft, Cogwheel, and Water Wheel recipes; Builder can now construct **Power Station Lv1**; **opens all 4 specialization branches**

**Building: Power Station** *(custom mod building — upgradeable)*  
The Power Station is the colony's central rotational power source. The Builder constructs and upgrades it; higher tiers provide more RPM and stress capacity for branch machines.  
- **Lv1** — unlocked by Rotational Power research *(baseline power for Andesite Age machines)*  
- **Lv2+** — unlocked by later research tiers; player defines speed/capacity upgrades per tier  
- *Power tier options TBD — player will specify upgrade paths*

**Optional Research:**

- **[Research: Engineer's Sight]** `andesite-age/ch1-engineers-sight`  
   - *Requires:* Rotational Power complete *(available any time after node 2)*  
   - *Consume:* 1x Shaft, 2x Iron Ingots, 2x Glass Panes  
   - *Unlocks:* Engineer's Goggles recipe; stress capacity and RPM become visible on machines  
   - *Note: More useful once branch machines are in place — not required to progress*

**Gate to Branches:** Completing **Rotational Power** research and Builder constructing **Power Station Lv1** unlocks all 4 branch choices.

---

## Branch 1: FARMING

### Chapter 2A — Crop Automation | FARMING BRANCH (Tier 3-4)

**Research:** `andesite-age/ch2a-crop-automation` (Requires Ch1)  
**Worker Requirements:** Blacksmith Lvl 2+, Farmer Lvl 2, Forester Lvl 2, Carpenter Lvl 2

*Farmer must reach Level 2 to unlock automation recipes. Automated tasks become available.*

First practical automation: automated food production to feed the colony.

**Workers involved:**
- **Blacksmith** (Lv3): Produces mechanical components (press, mills)
- **Forester** (Lv3): Provides structural materials
- **Farmer** (Lv2-3): Operates Millstone, early processing
- **Carpenter** (Lv3): Assembles frames and housing

**Core Milestones (→ required for Brass Age):**
7. Craft a **Mechanical Press** *(Shaft + Andesite Casing + Block of Iron)*
8. Press Iron Ingots into **Iron Sheets**
9. Craft a **Mechanical Belt** *(transport items between machines)*
10. Craft a **Basin** *(base for processing operations)*
11. Craft a **Millstone** *(Cogwheel + Andesite Casing + Stone)*
12. Automate **wheat processing** — Millstone grinds wheat to flour/dough

**Unlocks:** Farmer specialty in grain processing

### Chapter 3A — Advanced Crop Processing | FARMING BRANCH (Tier 4)

**Research:** `andesite-age/ch3a-advanced-crop-processing` (Requires Ch2A)  
**Worker Requirements:** Farmer Lvl 4+

*Farmer reaches Level 4 to unlock advanced milling and bulk washing. Complex automation chains available.*

**Workers involved:**
- **Farmer** (Lv4): Advanced milling and bulk washing
- **Crushers** (Lv3): Can assist with grinding operations

**Core Milestones:**
13. Craft an **Encased Fan** *(Shaft + Andesite Casing + Propeller)*
14. Build **Bulk Washing** setup *(Fan + Water = processing automation)*
15. Chain Millstone + Washer for full crop processing pipeline

**Optional Milestones (nice-to-have, not required):**
- Craft a **Mechanical Harvester** *(automate crop farms)*
- Build **Piston-driven plough** *(automated tilling)*

**Unlocks:** Farmer can operate advanced processing chains

---

## Branch 2: LOGGING

### Chapter 2B — Automated Logging | LOGGING BRANCH (Tier 3-4)

**Research:** `andesite-age/ch2b-automated-logging` (Requires Ch1)  
**Worker Requirements:** Blacksmith Lvl 2+, Forester Lvl 2, Carpenter Lvl 2

*Forester must reach Level 2 to unlock mechanical logging recipes. Automated tree processing available.*

Build the backbone for wood resource automation.

**Workers involved:**
- **Blacksmith** (Lv3): Produces mechanical components
- **Forester** (Lv3+): Primary operator of mechanical logging
- **Carpenter** (Lv3): Assembles wood-based components

**Core Milestones:**
7. Craft a **Mechanical Saw** *(Shaft + Andesite Casing + Blade parts)*
8. Build a **Moving Contraption** *(Mechanical Bearing/Piston + Chassis)*
9. Automate **log cutting** — Saw processes wood into planks/sticks

**Unlocks:** Forester specialty in automated logging

### Chapter 3B — Logging Scaling | LOGGING BRANCH (Tier 4-5)

**Research:** `andesite-age/ch3b-logging-scaling` (Requires Ch2B)  
**Worker Requirements:** Forester Lvl 4+, Carpenter Lvl 4

*Forester reaches Level 4+ to unlock advanced multi-machine logging. Large-scale automation available.*

**Workers involved:**
- **Forester** (Lv4-5): Advanced logging operations
- **Carpenter** (Lv4): Complex wood assembly

**Core Milestones:**
10. Build **stacked contraptions** *(multiple saws operating simultaneously)*
11. Implement **tree farm automation** *(coordinated planting + harvesting)*
12. Craft **Mechanical Drill** *(for mining specific blocks in logging operations)*

**Optional Milestones:**
- Build **log storage silos**
- Automated **planksmithy** operations

**Unlocks:** Advanced logging workflows

---

## Branch 3: ORE PROCESSING

### Chapter 2C — Ore Grinding Fundamentals | ORE PROCESSING BRANCH (Tier 3-4)

**Research:** `andesite-age/ch2c-ore-processing` (Requires Ch1)  
**Worker Requirements:** Blacksmith Lvl 2+, Crusher Lvl 2 (NEW), Smelter Lvl 2

*Crusher unlocks at Level 2 — this is when ore grinding automation becomes available. Previously, ore processing was purely manual.*

Build the foundation for industrial ore processing and refinement.

**Workers involved:**
- **Blacksmith** (Lv3): Produces processing machinery
- **Crusher** (Lv3): NEW SPECIALTY — operates mills and grinding equipment
- **Smelter** (Lv2-3): Early smelting operations

**Core Milestones:**
7. Craft a **Basin** *(base for all processing)*
8. Craft a **Millstone** *(Cogwheel + Andesite Casing + Stone)*
9. Automate **ore crushing** — Millstone grinds ores into dust
10. Mill **Zinc Ore** to get Zinc Ingots/Nuggets
11. Craft a **Mechanical Mixer** *(Cogwheel + Andesite Casing + Whisk, requires 30 RPM)*

**Unlocks:** Crusher specialty, Millstone operations

### Chapter 3C — Advanced Ore Processing | ORE PROCESSING BRANCH (Tier 4-5)

**Research:** `andesite-age/ch3c-advanced-crushing` (Requires Ch2C)  
**Worker Requirements:** Crusher Lvl 4+, Smelter Lvl 3+, Blacksmith Lvl 4

*Crusher reaches Level 4 to unlock Crushing Wheels and advanced washing chains. Large-scale ore processing pipelines available.*

**Workers involved:**
- **Crusher** (Lv4-5): Advanced crushing and washing
- **Smelter** (Lv3-4): Production scaling
- **Blacksmith** (Lv4): Advanced equipment production

**Core Milestones:**
12. Craft an **Encased Fan** *(Shaft + Andesite Casing + Propeller)*
13. Build **Bulk Washing** setup *(Fan + Water = free ore doubling from crushed ore)*
14. Chain Millstone/Crushing Wheels + Washer for full ore processing pipeline
15. Use **Mechanical Mixer** for efficient **Andesite Alloy** production

**Optional Milestones:**
- Build **advanced ore sorting systems**
- Implement **gravity-based ore routing**

**Unlocks:** Full ore processing chains, Crusher Lv5 capabilities

---

## Branch 4: WORKSHOP/CRAFTING

### Chapter 2D — Mechanical Crafting Prep | WORKSHOP BRANCH (Tier 3-4)

**Research:** `andesite-age/ch2d-workshop-foundation` (Requires Ch1)  
**Worker Requirements:** Blacksmith Lvl 2+, Carpenter Lvl 2, Builder Lvl 2

*Blacksmith Level 2 unlocks mechanical crafting component production. Automated component assembly available.*

Establish the colony's central production workshop.

**Workers involved:**
- **Blacksmith** (Lv3): Core component production
- **Carpenter** (Lv3): Crafting station assembly
- **Builder** (Lv3): Workshop construction and optimization

**Core Milestones:**
7. Craft a **Basin** *(base for crafting operations)*
8. Craft a **Mechanical Press** for **component compression**
9. Exploit **Press recipes** to create specialty components
10. Build **Press automation setup** *(chains of presses for bulk crafting)*
11. Craft **Mechanical Mixer** *(for advanced alloy mixing)*

**Unlocks:** Advanced crafting capabilities

### Chapter 3D — Mechanical Crafter Assembly | WORKSHOP BRANCH (Tier 4-5)

**Research:** `andesite-age/ch3d-mechanical-crafters` (Requires Ch2D)  
**Worker Requirements:** Blacksmith Lvl 4+, Carpenter Lvl 4+, Mechanic Lvl 2

*Blacksmith reaches Level 4+ and Mechanic becomes available at Level 2. Mechanical Crafter array assembly and compound recipe chains unlock.*

Prepare for Brass Age crafting through Mechanical Crafter arrays.

**Workers involved:**
- **Blacksmith** (Lv4-5): Precision component production
- **Carpenter** (Lv4-5): Crafter assembly and arrangement
- **Mechanic** (Lv3): Early precision work

**Core Milestones:**
12. Begin crafting **Mechanical Crafter blocks** (preparation)
13. Establish **Press + Mixer combinations** for compound recipes
14. Design **compact automation** for recipe chains
15. Prepare infrastructure for **Brass Age component crafting**

**Unlocks:** Mechanic specialty, Mechanical Crafter familiarity

---

## Convergence: Bridge to Brass Age

### Chapter 5 — Bridge to Brass (Tier 5) **[REQUIRED]**

**Research:** `andesite-age/ch5-bridge-to-brass` (Requires ALL 4 branches' ch3 completed)  
**Worker Requirements:** Blacksmith Lvl 5, Librarian Lvl 3, + Branch specialists Lvl 5

After mastering specializations, all paths converge for the heat-based breakthrough.  
*All branches must complete Ch3 before this gate opens. Librarian at Lvl 3 becomes available for research.*

**Workers involved:**
- **Librarian** (Lv3): Researches Blaze Burner technology
- **Blacksmith** (Lv5): Produces Mechanical Mixer and Brass components
- **All specialists** (Lv5): Final preparations

**Convergence Milestones:**
20. Craft a **Mechanical Mixer** to levels *(if not yet done)*
21. **Go to the Nether** — collect Blaze Rods
22. Craft a **Blaze Burner** *(the heat source that unlocks Brass)*
23. Activate heated recipes — ready for Brass Age

**Gate to Brass Age:** Must complete this chapter to proceed.

---

## 🔩 BRASS AGE (Unified)

All 4 specializations now **unified** into 1 progression path:

### Chapters 6-11: Brass Age Unified (Tier 6-10)

All workers advance together through coordinated complex automation:

- **Chapter 6**: Smelting Brass (heated mixing enabled)
- **Chapter 7**: Electronics & Sorting (Electron Tubes, sorting mechanics)
- **Chapter 8**: Mechanical Crafters (advanced recipe crafting)
- **Chapter 9**: Advanced Logistics (Deployers, Mechanical Arms, system integration)
- **Chapter 10**: Power Mastery (speed control, large wheels, efficiency)
- **Chapter 11**: Full Automation (complete automated systems, apex technology)

**Workers at Tier 10:**
- **Blacksmith** (Lv10): Ultimate component crafting
- **Mechanic** (Lv10): System design & oversight
- **Crusher** (Lv10): Advanced processing
- **Farmer** (Lv10): Integrated food systems
- **Forester** (Lv10): Integrated wood systems
- All workers coordinated in **fully automated mega-base**

---

---

## Worker Level Requirements

This table shows when Create automation unlocks for each worker:

| Worker | Lvl 1 | Lvl 2 (Automation Unlocks) | Lvl 3 | Lvl 4 (Advanced) | Lvl 5+ (Mastery) |
|--------|---|---|---|---|---|
| **Blacksmith** | Vanilla crafting | Crafts shafts, basic Create components (Ch1→2) | Production scaling | Complex components (Ch3) | Apex crafting (Ch5+) |
| **Farmer** | Vanilla farming | Millstone operations (Ch2A starts) | Scaling | Crushing Wheels, bulk washing (Ch3A) | Integrated systems (Ch5+) |
| **Forester** | Vanilla logging | Mechanical Saw (Ch2B starts) | Scaling | Multi-machine logging (Ch3B) | Large-scale systems (Ch5+) |
| **Crusher** | N/A (Lvl 2 start) | **UNLOCKS** — Millstone ops (Ch2C starts) | Scaling | Crushing Wheels (Ch3C) | Advanced grinding (Ch5+) |
| **Carpenter** | Vanilla building | Wood assembly (Ch2D) | Scaling | Complex wood mechanics (Ch3D) | Precision assembly (Ch5+) |
| **Smelter** | Vanilla smelting | Basic smelting | Production scaling | Heating recipes | Brass age heating (Ch5+) |
| **Mechanic** | N/A (Lvl 2 start) | **UNLOCKS** — Precision work (Ch3D) | Scaling | Deployers, Arms (Ch9) | System architect (Ch10+) |
| **Librarian** | Research only | Research only | Research gating (Ch5 gate) | — | — |

**Key Notes:**
- Lvl 1: All workers exist at baseline (vanilla operations only)
- **Lvl 2: Automation unlocks** — Create recipes become available for workers reaching this level
- Lvl 3-4: Advanced recipes unlock as workers level
- Lvl 5+: Brass Age and apex technologies
- **Crusher & Mechanic start at Lvl 2** — they don't have Lvl 1 vanilla equivalents

---

## Core vs Optional Path Summary

| Chapter | Branch | Core (→ Brass) | Optional (Quality of Life) |
|---------|--------|---|---|
| Ch 2A | Farming | Millstone, Press, Belts | Plough, Harvesters |
| Ch 2B | Logging | Mechanical Saw, Moving Contraption | Tree farm scaling |
| Ch 2C | Ore Processing | Crushing, Millstone, Mixer | Sorting, gravity routing |
| Ch 2D | Workshop | Press chains, Mixer | N/A |
| Ch 3A | Farming | Encased Fan, Bulk Wash | Advanced chains |
| Ch 3B | Logging | Stacked automation | Log storage |
| Ch 3C | Ore Processing | Crushing Wheels, Washing | Ore sorting |
| Ch 3D | Workshop | Crafters, recipe chains | Optimization |
| **Ch 5** | **Converge** | **Blaze Burner** | **—** |
| Ch 6-11 | Brass | All chapters sequential | Advanced versions |

---

## Worker Assignments

| Worker | First Branch | Progression | End Game |
|--------|---|---|---|
| **Blacksmith** | All (core production) | Lv1→3→5 | Lv10 (apex crafting) |
| **Carpenter** | All (assembly) | Lv2→3→4 | Lv10 (complex assembly) |
| **Forester** | Logging + general | Lv1→3→5 | Lv10 (integrated logging) |
| **Farmer** | Farming + optional | Lv2→4 | Lv10 (integrated crops) |
| **Crusher** | Ore Processing | Lv3→5 | Lv10 (advanced grinding) |
| **Smelter** | Ore Processing | Lv2→3 | Lv10 (all heating) |
| **Mechanic** | Workshop+Brass | Lv3→5 | Lv10 (system architect) |
| **Librarian** | Research gate | Lv3 | Lv3+ (continuous research) |
| **Miner** | N/A (no mine branch) | — | Available if needed |

---

## Research Dependencies

```
Ch 1: Rotational Foundations (Required)
    ↓
    ├→ Ch 2A: Crop Automation ────→ Ch 3A: Advanced Crops ─┐
    ├→ Ch 2B: Logging ────────────→ Ch 3B: Logging Scaling ┤
    ├→ Ch 2C: Ore Processing ─────→ Ch 3C: Advanced Crushing ┤
    └→ Ch 2D: Workshop ───────────→ Ch 3D: Mechanical Crafters ┘
        ↓
    Ch 5: Bridge to Brass (Requires all 4 core ch3 completions)
        ↓
    Ch 6-11: Brass Age (Sequential)
```

---

## Implementation Notes

- **All research use minecolonies research system**: `research-id` fields gate recipes
- **Core milestones unlock craftter recipes**, optional ones unlock convenience features
- **Branches can be done in any order**, but all 4 must complete Ch3X before accessing Ch5
- **Optional paths never gate progression** — purely additive QoL
- **Worker level progression** tied to chapter completion within each branch
