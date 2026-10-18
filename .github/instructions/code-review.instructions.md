---
name: Java Code Review Standards
description: Apply when reviewing Java, Spring Boot, API, or test changes.
---

# Java Code Review Instructions

## Required Instructions
Print:
[TRACE][INSTRUCTIONS] INSTRUCTIONS: Java Code Reviewer

## Review scope
1. Inspect the final Git diff and relevant surrounding code.
2. Compare the implementation against the stated requirements.
3. Follow existing project architecture and conventions.
4. Review independently; do not assume that passing tests prove correctness.

## Review for
- Functional correctness and edge cases
- Null handling and exception handling
- API compatibility and input validation
- Logging, observability, and error handling
- Test coverage
- Unrelated changes and maintainability

Only report actionable findings supported by evidence.

## Finding format
For each finding, include:

- Severity: Critical, High, Medium, or Low
- Location: file and line, where available
- Issue: what is wrong
- Impact: why it matters
- Recommendation: how to correct it

If there are no actionable findings, state that explicitly.
Mention any limitations in the review.

Do not edit files or apply fixes.
Do not commit, push, merge, or deploy.
