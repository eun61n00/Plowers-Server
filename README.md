### 📌 Commit message Types

| TYPE        | Description                                                 |
|-------------|-------------------------------------------------------------|
| **✨ FEAT**      | Add a new feature                                           |
| **🐛 FIX**      | Fix a bug                                                   |
| **📝 DOCS**     | Documentation changes (e.g., README file)                   |
| **🎀 STYLE**    | Code formatting (no code logic changes)                     |
| **♻️ REFACTOR** | Refactor code without changing functionality                |
| **✅ TEST**      | Add or update tests                                         |
| **🔧 CHORE**    | Miscellaneous tasks, like build processes or dependencies   |
| **⚡️ PERF**     | Performance improvements                                    |
| **🚀 CI/CD**    | CI/CD related changes                                       |
1.	Write the commit message subject in the format `[Type]: subject` No period at the end. 
2.	Capitalize the first letter of the subject.
3.	Use imperative mood in the subject: e.g., “Add feature”, “Fix bug”.
4.	Body is optional. If needed, leave a blank line between the subject and body.
5.	In the body, explain what and why the change was made, and wrap lines at 72 characters.


<br/>

### 📌 Branch Naming Rules
To maintain a clear and organized repository, follow these rules for creating and naming branches:

- **Main Branch**:  
  The main branch is called `main` and always contains production-ready code. All changes must be merged into `main` through pull requests.

- **Branch Types**:  
  Branches should be named based on the type of work being done. Use the following prefixes for clarity:

  | TYPE         | Description                                                                                 |
  |---------------------------------------------------------------------------------------------|-------------|
  | **feature/** | For adding new features  (e.g., `feature/user-authentication`)                              |
  | **fix/**     | For bug fixex (e.g., `fix/login-issue`)                                                     |
  | **hotfix/**  | For urgent production bug fixes  (e.g., `hotfix/security-vulnerability`)                    |
  | **chore/**   | For maintenance tasks like updating dependencies (e.g., `chore/update-dependencies`)        |
  | **refactor/** | For restructuring code without changing its behaviour (e.g., `refactor/user-service-logic`) |
  | **test/**      | For adding or modifying test cases (e.g., `test/add-login-tests`)                           |
  | **release/**    | For preparing a new release version (e.g., `release/v1.2.0`)                                |

1. **Use lowercase letters** and separate words with hyphens (`-`).
2. **Prefix the branch name** based on the type of work (e.g., `feature/`, `fix/`).
3. **Use descriptive names** that summarize the work being done.
4. Branch names should **relate to the Issue** being addressed (if applicable).