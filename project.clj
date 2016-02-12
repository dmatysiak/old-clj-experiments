(defproject organon "0.1.0-SNAPSHOT"
  :description "??"
  :url "http://github.com/dmatysiak/organon.git"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [net.mikera/core.matrix "0.49.0"]]
  :java-source-paths ["src/organon/java"]
  ;;:main ^:skip-aot organon.core
  :target-path "target/%s")
