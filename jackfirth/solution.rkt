#lang racket

(require fancy-app
         point-free
         graph)

(define string-length= (wind-pre* = string-length))

(define (string-of-length? n str)
  (= n (string-length str)))

(define (get-words-of-length n)
  (filter (string-of-length? n _)
          (file->lines "../words.txt")))

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

(struct word-graph (length words)
  #:methods gen:graph
  [(define (has-vertex? g v)
     (= (string-length v) (word-graph-length g)))
   (define (has-edge? g u v)
     (and (string-length= u v)
          (one-letter-difference? u v)))
   (define vertex=? string=?)
   ;; No mutation needed after initial graph construction
   (define-values
     (add-vertex! remove-vertex! add-edge! add-directed-edge! remove-edge! remove-directed-edge!)
     (values #f #f #f #f #f #f))
   (define (get-vertices g)
     (word-graph-words g))
   (define in-vertices get-vertices)
   (define (get-neighbors g v)
     (filter (one-letter-difference? v _)
             (word-graph-words g)))
   (define in-neighbors get-neighbors)
   ;; Not needed for simple BFS search
   (define-values
     (get-edges in-edges edge-weight transpose graph-copy)
     (values #f #f #f #f #f))])

(define (make-word-graph n)
  (word-graph n (get-words-of-length n)))

(define (path-to-word-from graph start-word goal-word)
  (let-values ([(_ predecessors) (bfs graph start-word)])
    (reverse (make-path-from-predecessors predecessors goal-word))))

(define (make-path-from-predecessors predecessors current)
  (let ([previous (hash-ref predecessors current)])
    (if previous
        (cons current (make-path-from-predecessors predecessors previous))
        (list current))))

;; examples

(define words-of-length-3 (make-word-graph 3))
(path-to-word-from words-of-length-3 "mad" "bun")