(set-env!
  :source-paths #{"src/clj" "src/cljc" "src/cljs"}
  :resource-paths #{"resources"}
  :dependencies '[[adzerk/boot-cljs "2.1.4" :scope "test"]
                  [adzerk/boot-reload "0.5.2" :scope "test"]
                  [adzerk/boot-cljs-repl   "0.3.3" :scope "test"]
                  ; used by boot-cljs-repl
                  [com.cemerick/piggieback "0.2.1" :scope "test"]
                  [weasel "0.7.0" :scope "test"]
                  [org.clojure/tools.nrepl "0.2.12" :scope "test"]
                  [javax.xml.bind/jaxb-api "2.3.0"] ; necessary for Java 9 compatibility
                  ; project deps
                  [org.clojure/clojure "1.9.0"]
                  [org.clojure/clojurescript "1.9.946" :scope "test"]
                  [reagent "0.7.0" :scope "test"]
                  [ring "1.5.1"]])

(task-options!
  pom {:project 'full-stack-boot-example
       :version "1.0.0-SNAPSHOT"
       :description "FIXME: write description"}
  aot {:namespace '#{full-stack-boot-example.core}}
  jar {:main 'full-stack-boot-example.core}
  sift {:include #{#"\.jar$"}})

(require
  '[adzerk.boot-cljs :refer [cljs]]
  '[adzerk.boot-reload :refer [reload]]
  '[adzerk.boot-cljs-repl :refer [start-repl cljs-repl]]
  'full-stack-boot-example.core)

(deftask run []
  (comp
    (with-pass-thru _
      (full-stack-boot-example.core/dev-main))
    (watch)
    (reload :asset-path "public")
    (cljs-repl :nrepl-opts {:port 9009})
    (cljs :source-map true :optimizations :none :compiler-options {:asset-path "main.out"})
    (target)))

(deftask build []
  (comp
    (cljs :optimizations :advanced)
    (aot)
    (pom)
    (uber)
    (jar)
    (sift)
    (target)))

