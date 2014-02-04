(ns aspire.templates-test
  (:require [net.cgrand.enlive-html :as en]
            [aspire.templates :refer :all]
            [clojure.test :refer :all]))

;; TODO: en/emit* and beautify the HTML in each test for human readability here?
(deftest onboarding-templates-test
  (testing "onboarding"
    (testing "snippets"
      (testing "common-header"
        (is (= (common-header 1 "a" "b")
               '({:tag :nav, :attrs {:id "top-bar"}, :content ["\n        " {:tag :header, :attrs {:id "logo"}, :content ["\n          " {:tag :h1, :attrs nil, :content ["\n            Logo\n          "]} "\n        "]} "\n        " {:tag :a, :attrs {:id "side-nav-control", :href "#", :data-active "false"}, :content ["Menu"]} {:tag :a, :attrs {:id "search-control", :href "#", :data-active "false"}, :content ["Search"]} "\n        " {:tag :ul, :attrs nil, :content ["\n          " {:tag :li, :attrs {:class "alerts"}, :content ["\n            " {:tag :a, :attrs {:href "/alerts"}, :content ["Alerts " {:tag :span, :attrs {:class "alert-count"}, :content ["1"]}]} "\n          "]} "\n          " {:tag :li, :attrs {:class "profile"}, :content ["\n            " {:tag :img, :attrs {:src "", :alt ""}, :content []} {:tag :a, :attrs {:href "a"}, :content ["b"]} "\n          "]} "\n          " {:tag :li, :attrs {:class "logout"}, :content ["\n            " {:tag :a, :attrs {:href "/logout"}, :content ["Logout"]} "\n          "]} "\n        "]} "\n      "]}))))

      (testing "onboarding-intro"
        (is (= (onboarding-intro "a" "b")
               '({:tag :div, :attrs {:class "intro"}, :content ["\n        " {:tag :h2, :attrs nil, :content ["a"]} "\n        " {:tag :p, :attrs nil, :content ("b")} "\n      "]})
               )))

      (testing "onboarding-step"
        (is (= (onboarding-step 1 "desc")
               '({:tag :li, :attrs {:class "step-1"}, :content ["\n          " {:tag :strong, :attrs nil, :content ["Step 1"]} " " {:tag :span, :attrs nil, :content ("desc")} "\n        "]})))))

    (testing "template"
      (is (= (onboarding 1 "a" "b" "c" "d" ["step1" "step2" "step3"])
             '("<!DOCTYPE html>\n" "<!--" "[if lt IE 7]>\n<html class=\"no-js lt-ie9 lt-ie8 lt-ie7\" lang=\"en\"></html>\n<![endif]" "-->" "<!--" "[if IE 7]>\n<html class=\"no-js lt-ie9 lt-ie8\" lang=\"en\"></html>\n<![endif]" "-->" "<!--" "[if IE 8]>\n<html class=\"no-js lt-ie9\" lang=\"en\"></html>\n<![endif]" "-->" "<!--" "[if gt IE 8]>\n<html class=\"no-js\" lang=\"en\"></html>\n<![endif]" "-->" "<" "html" ">" "<" "head" ">" "\n  " "<" "meta" " " "charset" "=\"" "utf-8" "\"" " />" "\n  " "<" "meta" " " "http-equiv" "=\"" "X-UA-Compatible" "\"" " " "content" "=\"" "IE=edge,chrome=1" "\"" " />" "\n  " "<" "title" ">" "Onboarding Page" "</" "title" ">" "\n  " "<" "meta" " " "name" "=\"" "description" "\"" " " "content" "=\"" "" "\"" " />" "\n  " "<" "meta" " " "name" "=\"" "viewport" "\"" " " "content" "=\"" "width=device-width" "\"" " />" "\n  " "<" "link" " " "type" "=\"" "text/css" "\"" " " "rel" "=\"" "stylesheet" "\"" " " "media" "=\"" "screen" "\"" " " "href" "=\"" "assets/stylesheets/global.css" "\"" " />" "<" "script" " " "type" "=\"" "text/javascript" "\"" " " "src" "=\"" "assets/javascripts/libs/modernizr.js" "\"" "></" "script" ">" "\n  " "</" "head" ">" "<" "body" " " "id" "=\"" "onboarding" "\"" " " "data-search-active" "=\"" "true" "\"" ">" "\n    " "<" "main" " " "role" "=\"" "main" "\"" " " "id" "=\"" "content" "\"" ">" "<" "div" " " "class" "=\"" "intro" "\"" ">" "\n        " "<" "h2" ">" "c" "</" "h2" ">" "\n        " "<" "p" ">" "d" "</" "p" ">" "\n      " "</" "div" ">" "\n      " "<" "ol" " " "class" "=\"" "onboarding-steps" "\"" ">" "<" "li" " " "class" "=\"" "step-1" "\"" ">" "\n          " "<" "strong" ">" "Step 1" "</" "strong" ">" " " "<" "span" ">" "step1" "</" "span" ">" "\n        " "</" "li" ">" "<" "li" " " "class" "=\"" "step-1" "\"" ">" "\n          " "<" "strong" ">" "Step 2" "</" "strong" ">" " " "<" "span" ">" "step2" "</" "span" ">" "\n        " "</" "li" ">" "<" "li" " " "class" "=\"" "step-1" "\"" ">" "\n          " "<" "strong" ">" "Step 3" "</" "strong" ">" " " "<" "span" ">" "step3" "</" "span" ">" "\n        " "</" "li" ">" "</" "ol" ">" "\n      " "<" "nav" " " "id" "=\"" "top-bar" "\"" ">" "\n        " "<" "header" " " "id" "=\"" "logo" "\"" ">" "\n          " "<" "h1" ">" "\n            Logo\n          " "</" "h1" ">" "\n        " "</" "header" ">" "\n        " "<" "a" " " "id" "=\"" "side-nav-control" "\"" " " "href" "=\"" "#" "\"" " " "data-active" "=\"" "false" "\"" ">" "Menu" "</" "a" ">" "<" "a" " " "id" "=\"" "search-control" "\"" " " "href" "=\"" "#" "\"" " " "data-active" "=\"" "true" "\"" ">" "Search" "</" "a" ">" "\n        " "<" "ul" ">" "\n          " "<" "li" " " "class" "=\"" "alerts" "\"" ">" "\n            " "<" "a" " " "href" "=\"" "/alerts" "\"" ">" "Alerts " "<" "span" " " "class" "=\"" "alert-count" "\"" ">" "1" "</" "span" ">" "</" "a" ">" "\n          " "</" "li" ">" "\n          " "<" "li" " " "class" "=\"" "profile" "\"" ">" "\n            " "<" "img" " " "src" "=\"" "" "\"" " " "alt" "=\"" "" "\"" " />" "<" "a" " " "href" "=\"" "a" "\"" ">" "b" "</" "a" ">" "\n          " "</" "li" ">" "\n          " "<" "li" " " "class" "=\"" "logout" "\"" ">" "\n            " "<" "a" " " "href" "=\"" "/logout" "\"" ">" "Logout" "</" "a" ">" "\n          " "</" "li" ">" "\n        " "</" "ul" ">" "\n      " "</" "nav" ">" "\n    " "</" "main" ">" "\n    " "<" "form" " " "id" "=\"" "search" "\"" " " "data-active" "=\"" "true" "\"" " " "action" "=\"" "/search-results-alt/" "\"" ">" "\n      " "<" "h2" ">" "\n        Search for classes &amp; competencies\n      " "</" "h2" ">" "\n      " "<" "input" " " "type" "=\"" "search" "\"" " " "placeholder" "=\"" "Search..." "\"" " />" "<" "button" " " "type" "=\"" "submit" "\"" " " "class" "=\"" "button-large" "\"" ">" "Search" "</" "button" ">" "\n    " "</" "form" ">" "\n    " "<" "nav" " " "role" "=\"" "navigation" "\"" " " "id" "=\"" "side-nav" "\"" " " "data-active" "=\"" "false" "\"" ">" "\n      " "<" "ul" ">" "\n        " "<" "li" " " "class" "=\"" "dashboard-nav" "\"" ">" "\n          " "<" "a" " " "href" "=\"" "dashboard" "\"" ">" "Dashboard" "</" "a" ">" "\n        " "</" "li" ">" "\n        " "<" "li" " " "class" "=\"" "competencies-nav" "\"" ">" "\n          " "<" "a" " " "href" "=\"" "competencies" "\"" ">" "Competencies" "</" "a" ">" "\n        " "</" "li" ">" "\n        " "<" "li" " " "class" "=\"" "learning-map-nav" "\"" ">" "\n          " "<" "a" " " "href" "=\"" "learning-map" "\"" ">" "Learning Map" "</" "a" ">" "\n        " "</" "li" ">" "\n        " "<" "li" " " "class" "=\"" "transcript-nav" "\"" ">" "\n          " "<" "a" " " "href" "=\"" "transcript" "\"" ">" "Transcript" "</" "a" ">" "\n        " "</" "li" ">" "\n      " "</" "ul" ">" "\n    " "</" "nav" ">" "\n    " "<" "footer" " " "role" "=\"" "contentinfo" "\"" "></" "footer" ">" "\n    " "<" "script" " " "type" "=\"" "text/javascript" "\"" " " "src" "=\"" "assets/javascripts/libs/jquery.js" "\"" "></" "script" ">" "\n    " "<" "script" " " "type" "=\"" "text/javascript" "\"" " " "src" "=\"" "assets/javascripts/global.js" "\"" "></" "script" ">" "\n  " "</" "body" ">" "\n" "</" "html" ">"))))))

