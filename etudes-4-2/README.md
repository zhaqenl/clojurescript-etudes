# Standalone Usage

1. `lein figwheel`
2. (In another window) `node target\js\compiled\etudes.js test.xml`


# Production Builds

1. `lein cljsbuild once prod`
2. `node server.js test.xml`


# Generated HTML

After successfully running `node` against the compiled file(s):

1. Open a browser window then go to [localhost:3000](localhost:3000)
2. Choose a condiment from the drop-down list
3. Click the submit button

- ![image](/etudes-4-2/images/image-1.png)
- ![image](/etudes-4-2/images/image-2.png)
- ![image](/etudes-4-2/images/image-3.png)


# REPL Usage (Vim)

You can now connect to Figwheel's REPL through
[Piggieback](https://github.com/cemerick/piggieback) using
[vim-fireplace](https://github.com/tpope/vim-fireplace):

1. `lein repl`
2. `(fig-start)`
3. `(cljs-repl)`
4. (In another window) `node target\js\compiled\etudes.js ...`
5. (In Vim) `:Piggieback (figwheel-sidecar.repl-api/repl-env)`

Standard `vim-fireplace` commands will now work in the context of the
Figwheel process:

- `cqp` to send a command from Vim to the REPL
- `cpa...` to evaluate a form without saving or reloading the file
- etc.
