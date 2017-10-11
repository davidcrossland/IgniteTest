package com.boa;

import org.apache.ignite.*;
import org.apache.ignite.binary.BinaryObject;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteRunnable;
import org.apache.ignite.resources.IgniteInstanceResource;

import javax.cache.Cache;

/**
 * Created by oddki on 11/10/17.
 */
public class Main {

    public static void main(String [] args)
    {
        Ignition.setClientMode(true);
        try (Ignite ignite = Ignition.start()) {
            IgniteCluster cluster = ignite.cluster();

            // Compute instance over remote nodes.
            IgniteCompute compute = ignite.compute(cluster.forRemotes());

            // Print hello message on all remote nodes.
            compute.broadcast(() -> System.out.println("Hello node: " + cluster.localNode().id()));
        }

    }
}
