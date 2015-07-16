(ns me.raynes.cegdown
  (:import (org.pegdown PegDownProcessor Extensions LinkRenderer)))

(def extensions
  "Mappings of keywords to extension constants."
  {:abbreviations        Extensions/ABBREVIATIONS
   :all                  Extensions/ALL
   :autolinks            Extensions/AUTOLINKS
   :anchorlinks          Extensions/ANCHORLINKS
   :definitions          Extensions/DEFINITIONS
   :fenced-code-blocks   Extensions/FENCED_CODE_BLOCKS
   :hardwraps            Extensions/HARDWRAPS
   :none                 Extensions/NONE
   :quotes               Extensions/QUOTES
   :smarts               Extensions/SMARTS
   :smartypants          Extensions/SMARTYPANTS
   :suppress-all-html    Extensions/SUPPRESS_ALL_HTML
   :suppress-html-blocks Extensions/SUPPRESS_HTML_BLOCKS
   :suppress-inline-html Extensions/SUPPRESS_INLINE_HTML
   :tables               Extensions/TABLES
   :wikilinks            Extensions/WIKILINKS
   :strikethrough        Extensions/STRIKETHROUGH})

(defn select-extensions
  "Take a list of extension keywords and get the bit-or'd extensions to pass
   to make-pegdown."
  [exts]
  (let [exts (filter identity (map extensions exts))]
    (int
     (if (> (count exts) 1)
       (apply bit-or exts)
       (first exts)))))

(defn make-pegdown
  "Make a PegDownProcessor instance with the provided extensions or a Parser
   instance."
  [exts-or-parser]
  (PegDownProcessor.
   (if (sequential? exts-or-parser)
     (select-extensions exts-or-parser)
     exts-or-parser)))

(defn to-html
  "Convert markdown to HTML. Takes at least one argument, a string of markdown.
   Optionally takes a 'target' which is either a PegDownProcessor instance,
   a sequence of extension keys, or a Parser object. Optional third argument is
   a LinkRenderer."
  ([s target link-renderer]
     (.markdownToHtml
      (if (instance? PegDownProcessor target)
        target
        (make-pegdown target))
      s
      link-renderer))
  ([s target]
     (to-html s target (LinkRenderer.)))
  ([s]
     (to-html s (make-pegdown [:none]) (LinkRenderer.))))
