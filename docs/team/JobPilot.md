# JobPilot- Project Portfolio Page

## Overview
JobPilot is a CLI job application tracker for fast typists to manage applications via commands like add, edit, delete, search, sort, and tag.

### Summary of Contributions

#### Labelle Lee
| Component     | Contribution                       | Key Implementation                                                                                                         |
|---------------|------------------------------------|----------------------------------------------------------------------------------------------------------------------------|
| Parser        | Built command routing system       | `ApplicationParser` routes to subparsers (`Adder`, `DeleterParser`, `EditorParser`, `Searcher`, `StatusUpdater`, `Tagger`) |
| Parser        | Implemented prefix-based parsing   | Extracts fields using `c/`, `p/`, `d/`, `s/` markers                                                                       |
| Parser        | Handled edge cases                 | Multi-word values (e.g., `p/Senior Software Engineer`) and extra whitespace                                                |
| Parser        | Created type-safe command handling | `CommandType` enum and `ParsedCommand` data class                                                                          |
| Application   | Designed core data structure       | `Application` class with `company`, `position`, `date`, `status`, `notes`, `industryTags`                                  |
| Application   | Added mutable field support        | Setter methods: `setCompany()`, `setPosition()`, `setDate()`, `setStatus()`, `setNotes()`                                  |
| Application   | Created tag management             | `IndustryTag` immutable class with `HashSet` for unique tags                                                               |
| Application   | Enabled sorting                    | `Comparable<Application>` implementation by submission date                                                                |
| Editor        | Implemented edit command           | Format: `edit INDEX [c/COMPANY] [p/POSITION] [d/DATE] [s/STATUS]`                                                          |
| Editor        | Supported partial updates          | Only specified fields are modified; others unchanged                                                                       |
| Editor        | Added validation                   | Non-empty values and `YYYY-MM-DD` date format                                                                              |
| Testing       | Wrote comprehensive tests          | 16 JUnit test methods covering success, error, and edge cases                                                              |


