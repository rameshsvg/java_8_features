# Java Workflow Orchestrator

This repository tracks and manages the step-by-step process for Java development, validation, and review.

## Workflow Diagram

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

## Stage Descriptions

1. **Java Workflow Orchestrator**: Manages state, assigns tasks, and coordinates overall workflow progress.
2. **Java Developer**: Implements software fixes, adds features, or addresses review/validation feedback.
3. **Java Validation**: Runs automated tests, linter checks, and static analysis tools.
   - If tests **fail**, control routes back to the **Developer**.
   - If tests **pass**, code proceeds to **Code Review**.
4. **Java Code Reviewer**: Performs an independent manual/diff code review.
   - If changes are **rejected**, control routes back to the **Developer**.
   - If changes are **approved**, execution completes.
5. **Finalize**: The Orchestrator aggregates final statuses, posts feedback, and closes the workflow.