package com.boa;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;
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
        try (Ignite ignite = Ignition.start("example-client.xml")) {
            System.out.println();
            System.out.println("Compute runnable example started.");

            IgniteCompute compute = ignite.compute();

            // Iterate through all words in the sentence and create runnable jobs.
            for (final String word : "Print words using runnable".split(" ")) {
                // Execute runnable on some node.
                compute.run(() -> {
                    System.out.println();
                    System.out.println(">>> Printing '" + word + "' on this node from ignite job.");
                });
            }

            System.out.println();
            System.out.println(">>> Finished printing words using runnable execution.");
            System.out.println(">>> Check all nodes for output (this node is also part of the cluster).");
        }

    }
}
