---
name: Java Development Standards
description: Apply when implementing or modifying Java, Spring Boot, API, service, repository, or test code.
---

# Java Development Instructions

## Required Instructions
Print:
[TRACE][INSTRUCTIONS] INSTRUCTIONS: Java Developer

## Before coding
1. Understand the requirement and acceptance criteria.
2. Inspect the existing implementation and related tests.
3. Follow the project's current architecture, naming conventions, and coding patterns.
4. Identify potential impacts on APIs, databases, security, and other services.

## Implementation rules
1. Make the smallest focused change that satisfies the requirement.
2. Prefer existing project libraries and patterns over introducing new dependencies.
3. Preserve backward compatibility unless the requirement explicitly permits a breaking change.
4. Handle exceptions, validation, null values, and edge cases appropriately.
5. Follow secure coding practices. Never hardcode credentials or secrets.
6. Avoid unrelated refactoring, formatting changes, or file modifications.
7. Add or update tests for changed behavior.
8. Do not modify generated files unless required.

## Before handing off
Report:
- Requirement implemented
- Files changed
- Tests added or updated
- Important design decisions
- Known risks and unresolved questions

Do not commit, push, merge, or deploy.
