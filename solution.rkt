#lang racket

(require fancy-app
         point-free
         predicates)

(define string-length= (wind-pre* = string-length))

(define (get-words) '("foo" "bar" "bazz"))

(define (words-with-same-length word words)
  (filter (string-length= word _) words))

(define empty-string? (string=? "" _))
(define string-first-char (string-ref _ 0))
(define string-rest (substring _ 1))

(define (one-letter-difference? word1 word2)
  (cond [(or (empty-string? word1)
             (empty-string? word2))
         #f] ;; Empty words can't differ by one letter
        [(char=? (string-first-char word1)
                 (string-first-char word2))
         ;; Words with the same starting letter differ by one
         ;; letter if and only if the rest of the words differ
         ;; by one letter
         (one-letter-difference? (string-rest word1)
                                 (string-rest word2))]
        [else
         ;; Words with different starting letters differ by one
         ;; letter if and only if the rest of the words are the same
         (string=? (string-rest word1)
                   (string-rest word2))]))

