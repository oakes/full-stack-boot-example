This is a sample project that demonstrates how to create a full stack (Clojure + ClojureScript) project using the [Boot](http://boot-clj.com/) build tool. With `boot run`, you can instantly see your project at [http://localhost:3000/](http://localhost:3000/) and any edits to the ClojureScript will be automatically pushed to the browser. With `boot build`, you can make a standalone JAR file that includes your entire client and server code. It also contains a project.clj file so it can be built with Leiningen as well.

## Build Instructions

* Install the latest JDK
* Install [Boot](http://boot-clj.com/) or [Leiningen](http://leiningen.org/)
* Develop with Boot: `boot run`
* Develop with Leiningen:`lein cljsbuild auto` and `lein run` (in separate terminals)
* Build JAR file with Boot: `boot build`
* Build JAR file with Leiningen: `lein cljsbuild once && lein uberjar`

## Contents

* `resources` The assets
* `src/clj` The server-side code
* `src/cljc` The client and server agnostic code
* `src/cljs` The client-side code
