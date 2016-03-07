(defproject full-stack-boot-example "1.0.0-SNAPSHOT"
  :description "An app for..."
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [reagent "0.6.0-alpha"]
                 [ring "1.4.0"]]
  :source-paths ["src/clj" "src/cljc"]
  :plugins [[lein-cljsbuild "1.1.2"]]
  :cljsbuild {:builds [{:source-paths ["src/cljs" "src/cljc"]
                        :compiler {:output-to "resources/public/full-stack-boot-example.js"
                                   :optimizations :advanced
                                   :pretty-print false}
                        :jar true}]}
  :aot [full-stack-boot-example.core]
  :main full-stack-boot-example.core)
