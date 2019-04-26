# Standalone Usage

1. `lein figwheel`
2. (In another window) `node target\js\compiled\etudes.js
   (small_file.csv|file.csv)` (Choose from either the smaller or larger file)
   
The main function is `frequency-table` which accepts two arguments--the
processed CSV file (which is named `map-heading-entry`) and any of the column
fields (:Gender, :Make, :Model, etc.).

After successfully running `node` on either file:

1. Go back to the `lein figwheel` window
2. `(in-ns 'etudes.core')`
3. `(frequency-table map-heading-entry :Gender)`
4. The output should be `[["F" "M" "U"] [6741 12780 7] 19528]`


# REPL Usage (Vim)

You can now connect to Figwheel's REPL through
[Piggieback](https://github.com/cemerick/piggieback) using
[vim-fireplace](https://github.com/tpope/vim-fireplace):

1. `lein repl`
2. `(fig-start)`
3. `(cljs-repl)`
4. (In another window) `node target\js\compiled\etudes.js (small_file.csv|file.csv)`
5. (In Vim) `:Piggieback (figwheel-sidecar.repl-api/repl-env)`

Standard `vim-fireplace` commands will now work in the context of the
Figwheel process:

- `cqp` to send a command from Vim to the REPL
- `cpa...` to evaluate a form without saving or reloading the file
- etc.
