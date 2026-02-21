# Project Guidelines

This repository contains a scalable notification platform.

**IMPORTANT:** These are general guidelines for the project. When working with specific prompts (`.claude/prompts/*.md`), the requirements in those prompts take **priority** over these guidelines. Technology choices, architecture decisions, and implementation details from prompts override the recommendations below.

## Repository Structure

/backend    → Backend services
/frontend   → Frontend application (Admin / UI)
/docs       → Documentation (architecture, ADR, API specs)

All changes must respect this structure.

---

# Backend

## Technology Guidelines
- Use explicit dependency versions (no LATEST, no version ranges)
- Minimize external dependencies - justify each addition
- Prefer standard libraries over third-party when reasonable

## Design Principles

- Follow SOLID principles.
- Prefer composition over inheritance.
- Business logic must not depend directly on infrastructure implementations.
- Clear separation of layers (domain / application / infrastructure).
- No "god classes".
- Explicit error handling (no swallowed exceptions).

## Reliability Rules

- All external calls must define explicit timeouts.
- Idempotency must be considered for all retryable operations.
- No unbounded in-memory queues.
- Failures must be classified (transient vs permanent).

## Observability

- All critical flows must expose:
    - latency metrics
    - success/failure counters
    - structured logs
- Never log secrets or sensitive data.

## Testing Requirements

- All new business logic must include unit tests.
- DB/repository logic requires integration tests.
- Message consumers require integration tests.
- Tests must be deterministic (no sleep-based logic).

---

# Frontend

## Stack
- React 18
- TypeScript (strict mode)
- Vite
- MUI (Material UI)
- TanStack Query

## Requirements

- Strict TypeScript mode must remain enabled.
- No usage of `any` unless justified.
- API contracts must be generated from OpenAPI when possible.
- ESLint + Prettier must pass before merge.

---

# Documentation

- Architecture decisions must be recorded in /docs/adr.
- Public APIs must have OpenAPI specification.
- Major changes must update relevant documentation.

---

# Change Management

- No breaking API changes without versioning.
- Database changes must include migration scripts.
- New dependencies require justification.
- Pull requests must be small and focused.
