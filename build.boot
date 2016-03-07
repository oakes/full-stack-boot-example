(set-env!
  :source-paths #{"src/clj" "src/cljc" "src/cljs"}
  :resource-paths #{"resources"}
  :dependencies '[[adzerk/boot-cljs "1.7.228-1" :scope "test"]
                  [adzerk/boot-cljs-repl "0.3.0" :scope "test"]
                  [adzerk/boot-reload "0.4.4" :scope "test"]
                  [com.cemerick/piggieback "0.2.1" :scope "test"]
                  [weasel "0.7.0" :scope "test"]
                  [org.clojure/tools.nrepl "0.2.12" :scope "test"]
                  ; project deps
                  [org.clojure/clojure "1.8.0"]
                  [org.clojure/clojurescript "1.7.228"]
                  [reagent "0.6.0-alpha"]
                  [ring "1.4.0"]])

(task-options!
  pom {:project 'full-stack-boot-example
       :version "1.0.0-SNAPSHOT"}
  aot {:namespace '#{full-stack-boot-example.core}}
  jar {:main 'full-stack-boot-example.core})

(require
  '[adzerk.boot-cljs :refer [cljs]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl]]
  '[adzerk.boot-reload :refer [reload]])

(deftask run-cljs []
  (comp (watch)
        (reload :asset-path "public"
                :on-jsload 'full-stack-boot-example.core/init!)
        (cljs-repl)
        (cljs :source-map true :optimizations :none)))

(deftask run-clj []
  (with-pre-wrap fileset
    (require '[full-stack-boot-example.core :refer [dev-main]])
    ((resolve 'dev-main))
    fileset))

(deftask run []
  (comp (run-cljs) (run-clj)))

(deftask build-cljs []
  (comp (cljs :optimizations :advanced)))

(deftask build-clj []
  (comp (aot) (pom) (uber) (jar)))

(deftask build []
  (comp (build-cljs) (build-clj)))
