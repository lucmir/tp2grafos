#!/usr/bin/env python

import networkx as nx

N = 100
E = int(round((N**2)/1.5))

dense = nx.dense_gnm_random_graph(N, E)
nx.write_edgelist(dense, 'dense.dat', data=False)

karate = nx.karate_club_graph()
nx.write_edgelist(karate, 'karate.dat', data=False)

plaw = nx.powerlaw_cluster_graph(N * 10, N/10, 0.3)
nx.write_edgelist(plaw, 'plaw.dat', data=False)
