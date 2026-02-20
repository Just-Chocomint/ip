# Fern User Guide

![Fern UI](/docs/Ui.png)

Fern is a chatbot that helps you keep track of your tasks efficiently. Whether you have simple to-do items, deadlines, or events spanning multiple days, Fern has got you covered. This User Guide is generated with the help of AI

---

## Table of Contents
- [Getting Started](#getting-started)
- [Features](#features)
  - [Listing Tasks](#-listing-tasks)
  - [Adding Tasks](#-adding-tasks)
    - [To-Do Tasks](#-to-do-tasks)
    - [Deadline Tasks](#-deadline-tasks)
    - [Event Tasks](#-event-tasks)
  - [Managing Tasks](#managing-tasks)
    - [Marking Tasks as Complete](#-marking-tasks-as-complete)
    - [Unmarking Tasks](#-unmarking-tasks)
    - [Deleting Tasks](#-deleting-tasks)
    - [Finding Tasks](#-finding-tasks)
- [Date Formats](#date-formats)


## Getting Started

### How to Download:
1. Download the application from [here](https://github.com/Just-Chocomint/ip/releases)
2. Run the application and start managing your tasks! 😄

### Tested Platforms

Fern has been tested on the following platforms:

**Windows 11 Home**
- Java 24.0.1
- Java 17.0.12

**Kali Linux 2024.2 (Rolling) Debian-based**
- Java 21.0.3

**macOS 15.7.3**
- Java 17.0.14

---
## Features

### 📋 Listing Tasks

View all your current tasks with their completion status.

**Command:** `list`

**Example:**
```
list
```

**Expected Output:**
```
01. [To Do][ ] Read book
02. [Deadline][X] Submit assignment (by: 25/02/2026)
03. [Event][ ] Team meeting (From: 26/02/2026 | To: 26/02/2026)
```

---

### ➕ Adding Tasks

#### 📝 To-Do Tasks

Add simple tasks without any dates.

**Command:** `todo <task_description>`

**Example:**
```
todo Read book
```

**Expected Output:**
```
Added ToDo (Read book)
```

<br>

#### ⏰ Deadline Tasks

Add tasks that need to be completed by a specific date.

**Command:** `deadline <task_description> /by <deadline>`

**Example:**
```
deadline Submit assignment /by 25/02/2026
```

**Expected Output:**
```
Added Deadline (Submit assignment)
```

<br>

#### 📅 Event Tasks

Add events that span from a start date to an end date.

**Command:** `event <task_description> /from <start_date> /to <end_date>`

**Example:**
```
event Team meeting /from 26/02/2026 /to 27/02/2026
```

**Expected Output:**
```
Added Event (Team meeting)
```

<br>

---

### Managing Tasks

#### ✅ Marking Tasks as Complete

Mark a task as completed using its number from the list.

**Command:** `mark <task_number>`

**Example:**
```
mark 1
```

**Expected Output:**
```
(Read book) marked
```

<br>

#### ↩️ Unmarking Tasks

Unmark a task to mark it as incomplete again.

**Command:** `unmark <task_number>`

**Example:**
```
unmark 1
```

**Expected Output:**
```
(Read book) unmarked
```

<br>

#### 🗑️ Deleting Tasks

Remove a task from your list permanently.

**Command:** `delete <task_number>`

**Example:**
```
delete 1
```

**Expected Output:**
```
([To Do][ ] Read book) deleted. Left 2 Tasks
```

<br>

#### 🔍 Finding Tasks

Search for tasks containing a specific keyword.

**Command:** `find <keyword>`

**Example:**
```
find book
```

**Expected Output:**
```
01. [To Do][ ] Read book
```

---
## Date Formats

Fern supports multiple date formats for your convenience:

- **Numeric format:** `25/2/2026` or `25/02/2026`
- **Short month names:** `25 Feb 2026` or `25 Feb`
- **Full month names:** `25 February 2026` or `25 February`
- **Weekday names:** `monday`, `mon`, `tuesday`, `tue`, etc. (automatically finds the next occurrence)

**Examples:**
```
deadline Meeting /by monday
event Conference /from 15 Mar /to 17 Mar
todo Call mom
```

---

Enjoy using Fern to manage your tasks efficiently! 🌿
