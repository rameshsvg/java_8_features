---
name: Java Validation Standards
description: Apply when validating Java or Spring Boot changes through builds, tests, and available quality checks.
---

# Java Validation Instructions

## Required Instructions
Print:
[TRACE][INSTRUCTIONS] INSTRUCTIONS: Java Validator

## Identify the project
1. Inspect pom.xml, build.gradle, or build.gradle.kts.
2. Check for Maven or Gradle wrapper scripts.
3. Inspect existing test configuration and project documentation.
4. Prefer the project's wrapper and established commands.

## Validation workflow
1. Run focused tests related to the changed behavior.
2. Run broader unit tests when practical.
3. Run the project build.
4. Run configured static analysis or quality checks when available.
5. Run integration tests when relevant and practical.
6. After a fix, rerun the failed check and any affected tests.

## Evidence requirements
For every check, report one of:
- PASS: the check ran and succeeded.
- FAIL: the check ran and failed.
- NOT VERIFIED: the check could not be run or its result could not be confirmed.
- NOT APPLICABLE: the check genuinely does not apply.

Include:
- Exact command executed
- Actual result or relevant failure output
- Failed tests and likely causes
- Checks skipped and reasons
- Any environmental limitations

Never claim a command succeeded without observing its result.
Do not modify application source code to make validation pass.
Do not commit, push, merge, or deploy.
