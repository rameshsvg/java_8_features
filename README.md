# Java Workflow Orchestrator

This repository tracks and manages the step-by-step process for Java development, validation, and review.

## Repository Customization Structure

```text
.github/
├── copilot-instructions.md
├── instructions/
│   ├── code-review.instructions.md
│   ├── development.instructions.md
│   └── validation.instructions.md
└── agents/
    ├── java-code-reviewer.agent.md
    ├── java-developer.agent.md
    ├── java-validator.agent.md
    └── java-workflow-orchestrator.agent.md
```

## Workflow Process Overview

```mermaid
flowchart TD
    Orchestrator["<div style='min-width:350px; height:20px;'><b>Java Workflow Orchestrator</b><br/><small>Coordinates the full workflow</small>"]
    Developer["<div style='min-width:350px; height:20px;'><b>Java Developer</b><br/><small>Reads development instructions · Implements changes</small>"]
    Validation["<div style='min-width:350px; height:20px;'><b>Java Validation</b><br/><small>Reads validation instructions · Runs available checks</small>"]
    Reviewer["<div style='min-width:350px; height:20px;'><b>Java Code Reviewer</b><br/><small>Reads review instructions · Independently reviews the diff</small>"]
    Reporter["<div style='min-width:350px; height:20px;'><b>Orchestrator reports results</b><br/><small>Coordinates fixes and revalidation if needed</small>"]

    Orchestrator --> Developer
    Developer --> Validation
    Validation --> Reviewer
    Reviewer --> Reporter

    %% Custom Styles & Wide Padding
    classDef orchestratorStyle fill:#fef3c7,stroke:#f59e0b,stroke-width:1px,color:#78350f,padding:10px;
    classDef developerStyle fill:#f3e8ff,stroke:#a855f7,stroke-width:2px,color:#581c87,padding:10px;
    classDef validationStyle fill:#e0f2fe,stroke:#0284c7,stroke-width:2px,color:#0369a1,padding:10px;
    classDef reviewerStyle fill:#ffedd5,stroke:#f97316,stroke-width:2px,color:#9a3412,padding:10px;
    classDef reporterStyle fill:#dcfce7,stroke:#22c55e,stroke-width:2px,color:#15803d,padding:10px;

    class Orchestrator orchestratorStyle;
    class Developer developerStyle;
    class Validation validationStyle;
    class Reviewer reviewerStyle;
    class Reporter reporterStyle;
```

## Stage Breakdown Reference

| Stage | Role | Responsibilities |
| :----- | :----- | :----- |
| 1. Orchestrator | Java Workflow Orchestrator | Coordinates the full workflow from start to finish. |
| 2. Development | Java Developer | Reads development instructions and implements required changes. |
| 3. Validation | Java Validation | Reads validation instructions and executes available checks. |
| 4. Code Review | Java Code Reviewer | Reads review instructions and independently reviews the diff. |
| 5. Reporting | Orchestrator reports results | Coordinates fixes and revalidation if needed. |

## Workflow Architecture Diagram

```mermaid
flowchart TD
    Start([Start Workflow]) --> Orchestrator[Java Workflow Orchestrator<br/><i>Coordinates all stages</i>]
    
    Orchestrator --> Developer[Java Developer<br><i>Implements / fixes code</i>]
    
    Developer --> Validation[Java Validation<br/><i>Runs available checks</i>]
    
    Validation --> ValDecision{Validation<br/>passed?}
    
    ValDecision -- No --> Developer
    ValDecision -- Yes --> Reviewer[Java Code Reviewer<br><i>Independently reviews diff</i>]
    
    Reviewer --> RevDecision{Review<br/>approved?}
    
    RevDecision -- No --> Developer
    RevDecision -- Yes --> Finalize[Orchestrator Finalizes<br><i>Aggregates final results</i>]
    
    Finalize --> End([Workflow Complete])

    %% Styling
    style Start fill:#eff6ff,stroke:#3b82f6,stroke-width:2px,color:#0f172a
    style End fill:#f0fdf4,stroke:#22c55e,stroke-width:2px,color:#0f172a
    style Orchestrator fill:#fef3c7,stroke:#f59e0b,stroke-width:2px,color:#0f172a
    style Finalize fill:#fef3c7,stroke:#f59e0b,stroke-width:2px,color:#0f172a
    style Developer fill:#f3e8ff,stroke:#a855f7,stroke-width:2px,color:#0f172a
    style Validation fill:#f3e8ff,stroke:#a855f7,stroke-width:2px,color:#0f172a
    style Reviewer fill:#f3e8ff,stroke:#a855f7,stroke-width:2px,color:#0f172a
    style ValDecision fill:#ffedd5,stroke:#f97316,stroke-width:2px,color:#0f172a
    style RevDecision fill:#ffedd5,stroke:#f97316,stroke-width:2px,color:#0f172a

    %% Link styles for clean loopbacks
    linkStyle 2 stroke:#ef4444,stroke-width:2px,stroke-dasharray: 4 4;
    linkStyle 5 stroke:#ef4444,stroke-width:2px,stroke-dasharray: 4 4;
```

## Detailed Workflow Descriptions

1. **Java Workflow Orchestrator**: Manages state, assigns tasks, and coordinates overall workflow progress.
2. **Java Developer**: Implements software fixes, adds features, or addresses review/validation feedback.
3. **Java Validation**: Runs automated tests, linter checks, and static analysis tools.
   - If tests **fail**, control routes back to the **Developer**.
   - If tests **pass**, code proceeds to **Code Review**.
4. **Java Code Reviewer**: Performs an independent manual/diff code review.
   - If changes are **rejected**, control routes back to the **Developer**.
   - If changes are **approved**, execution completes.
5. **Finalize**: The Orchestrator aggregates final statuses, posts feedback, and closes the workflow.