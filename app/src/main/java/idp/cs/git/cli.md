#### $git config

Change global data

`git config --global user.name "Kolosek`


#### $git init

Init git repo in folder

#### $git clone ?(-b <branch_name>) <path>

Copy remote repo

`git clone /path/repository`

#### $git add <file_name>

Add file/s changes to index

#### $git commit ?(-m <commit_message>) ?(-a)

Create changes from index (-a since then)

`git commit -m "Commit these changes."`

#### $git remote

All remote repos

#### $git checkout ?(-b) <branch_name>

Switch to new(-b) or existed branch

#### $git branch ?(-a) ?(<branch_name>)

List of branches plus remote(-a) or create new(<branch_name>)

#### $git push

Send local changes to remote

`git push origin <branch_name>` or delete `git push origin :<branch_name>`

#### $git pull

Fetch and merge remote in local

#### $git merge <branch_name>

Merge in active branch another

#### $git diff ?(<source_branch> <target_branch>)

Changes in active branch and index or custom

#### $git reset

Remove index and folder changes to last commit
--soft keep changes
--hard full erase

#### $git revert

Same as rest but create commit

#### $git tag

Badge for enjoyers

`git tag 1.0.0 <commit_id>`

#### $git log

List of log as commit_id, author, date and commits

``

