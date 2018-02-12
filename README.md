This is a sample project that demonstrates how to create a full stack (Clojure + ClojureScript) project using the [Boot](http://boot-clj.com/) build tool. With `boot run`, you can instantly see your project at [http://localhost:3000/](http://localhost:3000/) and any edits to the ClojureScript will be automatically pushed to the browser. With `boot build`, you can make a standalone JAR file that includes your entire client and server code.

## Build Instructions

* Install the latest JDK
* Install [Boot](http://boot-clj.com/)
* Develop with `boot run`
* Build JAR file with `boot build`

## Contents

* `resources` The assets
* `src/clj` The server-side code
* `src/cljc` The client and server agnostic code
* `src/cljs` The client-side code

## Connecting to the REPL
After starting the development environment with `boot run`, open up a new terminal and run `boot repl -c`.

This will only launch the **repl client**.
Once it has loaded you need to connect to the **cljs-repl** with `(start-repl)`. Now go to your browser and open up **http://localhost:3000**.
Output similar to this should appear: 
```

boot.user=> (start-repl)
<< started Weasel server on ws://127.0.0.1:41101 >>
<< waiting for client to connect ... Connection is ws://localhost:41101
Writing boot_cljs_repl.cljs...
 connected! >>
To quit, type: :cljs/quit
nil
cljs.user=>

```
To test the **REPL** type `(js/alert "Hello")`, which should open an alert box in your browser.

### Connecting with other tools
If you would like to connect with other tools, like your editor, you can do so via Port **9009**.

### Connecting a server REPL
Running `boot run` and `boot repl -c` lets you evaluate ClojureScript code. For running server code, you need a server repl.

**Step one**: Launch a server REPL server with `boot repl -s run`. This lets you plug into the Clojure backend instead of just the ClojureScript frontend. You should see something like this:

```
$ boot repl -s run
2018-02-05 10:03:07.145:INFO::main: Logging initialized @3448ms
Starting reload server on ws://localhost:34735
Writing boot_cljs_repl.cljs...
nREPL server started on port 41183 on host 127.0.0.1 - nrepl://127.0.0.1:41183
2018-02-05 10:03:09.652:INFO:oejs.Server:clojure-agent-send-off-pool-0: jetty-9.2.10.v20150310
2018-02-05 10:03:09.872:INFO:oejs.ServerConnector:clojure-agent-send-off-pool-0: Started ServerConnector@77d270e3{HTTP/1.1}{0.0.0.0:3000}
2018-02-05 10:03:09.872:INFO:oejs.Server:clojure-agent-send-off-pool-0: Started @6175ms

Starting file watcher (CTRL-C to quit)...

Writing adzerk/boot_reload/public$/main.cljs to connect to ws://localhost:34735...
Adding :require adzerk.boot-reload.public$.main to public/main.cljs.edn...
nREPL server started on port 9009 on host 127.0.0.1 - nrepl://127.0.0.1:9009
Adding :require adzerk.boot-cljs-repl to main.cljs.edn...
Compiling ClojureScript...
• public/main.js
Writing target dir(s)...
Elapsed time: 14.898 sec
```

The backend REPL server started on port 41183 (port 9009 is the frontend REPL server).

**Step two**: connect to the server repl. In Emacs, I can open `src/clj/full_stack_boot_example/core.clj` and connect with `M-x cider-connect` to `localhost` port `41183`. Your port may vary!
