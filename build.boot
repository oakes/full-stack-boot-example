(set-env!
  :source-paths #{"src/clj" "src/cljc" "src/cljs"}
  :resource-paths #{"resources"}
  :dependencies '[[adzerk/boot-cljs "1.7.228-1" :scope "test"]
                  [adzerk/boot-reload "0.4.12" :scope "test"]
                  ; project deps
                  [org.clojure/clojure "1.8.0"]
                  [org.clojure/clojurescript "1.9.225"]
                  [reagent "0.6.0"]
                  [ring "1.4.0"]])

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
  'full-stack-boot-example.core)

(deftask run []
  (comp
    (watch)
    (reload :asset-path "public"
            :on-jsload 'full-stack-boot-example.core/init)
    (cljs :source-map true :optimizations :none)
    (target)
    (with-pass-thru _
      (full-stack-boot-example.core/-main))))

(deftask build []
  (comp
    (cljs :optimizations :advanced)
    (aot)
    (pom)
    (uber)
    (jar)
    (sift)
    (target)))

