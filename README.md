# GitHub Copilot Customization Reference

A quick reference for understanding the purpose of the main GitHub Copilot customization files in this repository.

## Quick decision guide

| Question | Use this file | Purpose | Calling / activation mechanism | Short note |
|---|---|---|---|---|
| Should Copilot always know this rule? | `.github/copilot-instructions.md` | Defines general project-wide guidance, such as coding conventions, architecture expectations, and shared constraints. | Automatically included for relevant Copilot requests when instruction-file support is enabled and configured. | Keep it concise and broadly applicable. Avoid task-specific workflows here. |
| Does the rule apply only to a specific task or file type? | `.github/instructions/*.instructions.md` | Adds focused instructions for a task or matching files, such as Java conventions, explanation rules, or review guidance. | Applied when its `applyTo` glob matches the relevant file paths, or discovered as relevant through its description where supported. | `applyTo` matches file paths, not keywords in the prompt. Description-based discovery is best-effort. |
| Do you want to run a predefined task with `/`? | `.github/prompts/*.prompt.md` | Defines a reusable workflow, such as implementing a feature, reviewing changes, or explaining a Java program. | Explicitly invoked in Chat using its slash command, for example `/developer`, `/reviewer`, or `/explainer`. | Best for repeatable tasks and consistent output formats. |
| Do you want a dedicated role with particular tools and behavior? | `.github/agents/*.agent.md` | Defines a specialized agent role, instructions, and available tools. | Usually selected from the agent picker in Chat; availability and behavior depend on the selected harness and configuration. | Best when you want to work with a specialist across a conversation or control tool access. |

## Example repository layout

```text
.github/
├── copilot-instructions.md
├── instructions/
│   └── java-explanator.instructions.md
├── prompts/
│   ├── developer.prompt.md
│   ├── reviewer.prompt.md
│   └── explainer.prompt.md
└── agents/
    ├── program-explanation.agent.md
    └── stream-api-explanation.agent.md
```

## How the files work together

- **Project-wide rules:** `copilot-instructions.md` provides shared guidance.
- **Focused rules:** `*.instructions.md` adds instructions when relevant.
- **Task workflows:** `*.prompt.md` tells Copilot how to perform a particular task when invoked.
- **Specialist roles:** `*.agent.md` configures a role and, where appropriate, its tools.

These mechanisms can work together. For example, invoking `/reviewer` can run the review workflow while the project-wide rules and relevant code-review instructions also guide the task.

## Practical examples

| User request | Suggested mechanism |
|---|---|
| “Add email validation to the existing service.” | General project rules + relevant instructions; use `/developer` for a repeatable implementation workflow. |
| “Review the modified Java source without changing it.” | Use `/reviewer` for the repeatable review workflow, alongside applicable rules. |
| “Explain the logic in `StreamAPI.java`.” | Use `/explainer`, or select a specialized explanation agent if you want that role. |
| “Always follow existing naming conventions.” | Put the rule in `copilot-instructions.md`. |
| “For Java files, apply this coding convention.” | Use an instruction file with a suitable `applyTo` pattern such as `**/*.java`. |

## Important notes

1. `applyTo` is a file-path glob; it is not a prompt-keyword trigger.
2. Instruction discovery based on descriptions is best-effort and may vary by VS Code version and agent harness.
3. Prompt files are explicitly invoked; they do not automatically run just because a matching word appears in a prompt.
4. Agent files define specialized roles; they are not the same as prompt files.
5. Keep shared rules in one place and reference them where possible instead of duplicating long instructions.

## Quick memory aid

- **Always know this rule?** → `copilot-instructions.md`
- **Rule for certain files or tasks?** → `*.instructions.md`
- **Run a saved workflow with `/`?** → `*.prompt.md`
- **Use a specialized role/toolset?** → `*.agent.md`
