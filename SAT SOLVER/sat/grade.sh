java -Xss10m -Xmx20g -jar sat.jar \
-a \
solvers/smosely1.jar \
solvers/random.jar \
solvers/brute.jar \
solvers/gsat.jar \
solvers/walksat.jar \
solvers/dpll.jar \
-p \
benchmarks/true.sat \
benchmarks/false.sat \
benchmarks/positive_literal.sat \
benchmarks/negative_literal.sat \
benchmarks/disjunction.sat \
benchmarks/conjunction.sat \
benchmarks/contradiction.sat \
benchmarks/cnf_1.sat \
benchmarks/cnf_2.sat \
benchmarks/cnf_3.sat \
benchmarks/graph_coloring_easy_1.sat \
benchmarks/graph_coloring_easy_2.sat \
benchmarks/graph_coloring_easy_3.sat \
benchmarks/graph_coloring_easy_4.sat \
benchmarks/graph_coloring_easy_5.sat \
benchmarks/graph_coloring_hard_1.sat \
benchmarks/graph_coloring_hard_2.sat \
benchmarks/graph_coloring_hard_3.sat \
benchmarks/graph_coloring_hard_4.sat \
benchmarks/graph_coloring_hard_5.sat \
benchmarks/3sat_easy_1.sat \
benchmarks/3sat_easy_2.sat \
benchmarks/3sat_easy_3.sat \
benchmarks/3sat_easy_4.sat \
benchmarks/3sat_easy_5.sat \
benchmarks/3sat_medium_1.sat \
benchmarks/3sat_medium_2.sat \
benchmarks/3sat_medium_3.sat \
benchmarks/3sat_medium_4.sat \
benchmarks/3sat_medium_5.sat \
benchmarks/3sat_hard_1.sat \
benchmarks/3sat_hard_2.sat \
benchmarks/3sat_hard_3.sat \
benchmarks/3sat_hard_4.sat \
benchmarks/3sat_hard_5.sat \
benchmarks/bb_cake_eat_cake_time_1.sat \
benchmarks/bb_cake_have_eat_cake_time_2.sat \
benchmarks/bb_blocks_easy_stack_time_1.sat \
benchmarks/bb_blocks_easy_unstack_time_1.sat \
benchmarks/bb_blocks_sussman_time_3.sat \
benchmarks/bb_blocks_reverse_2_time_2.sat \
benchmarks/bb_blocks_reverse_4_time_5.sat \
benchmarks/bb_blocks_reverse_6_time_7.sat \
benchmarks/bb_blocks_reverse_8_time_9.sat \
benchmarks/bb_blocks_reverse_10_time_11.sat \
benchmarks/bb_blocks_reverse_12_time_13.sat \
benchmarks/bb_cargo_deliver_1_time_3.sat \
benchmarks/bb_cargo_deliver_2_time_3.sat \
benchmarks/bb_cargo_deliver_3_time_5.sat \
benchmarks/bb_cargo_deliver_return_1_time_4.sat \
benchmarks/bb_cargo_deliver_return_2_time_4.sat \
benchmarks/bb_wumpus_easy_wumpus_time_3.sat \
benchmarks/bb_wumpus_medium_wumpus_time_7.sat \
-ol 100000 \
-tl 300000 \
-o results.html
