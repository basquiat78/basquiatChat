package com.basquiat.chat;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;

/**
 * Created by uEngine on 2017-04-10.
 */
public class TEST {
    public static void main(String... args) throws Exception {
        // Initialize the Connection
        Cluster cluster = CouchbaseCluster.create("localhost");
        Bucket bucket = cluster.openBucket("test");

        // Create a JSON Document
        JsonObject test = JsonObject.create()
                .put("name", "Arthur")
                .put("email", "kingarthur@couchbase.comsdfsafd");

        // Store the Document
        bucket.upsert(JsonDocument.create("u:test", test));

        // Load the Document and print it
        // Prints Content and Metadata of the stored Document
        System.out.println(bucket.get("u:test"));

        // Create a N1QL Primary Index (but ignore if it exists)
        bucket.bucketManager().createN1qlPrimaryIndex(true, false);

        // Perform a N1QL Query
        N1qlQueryResult result = bucket.query(
                N1qlQuery.parameterized("SELECT name FROM test WHERE $1 IN email",
                        JsonArray.from("kingarthur@couchbase.com"))
        );

        // Print each found Row
        for (N1qlQueryRow row : result) {
            // Prints {"name":"Arthur"}
            System.out.println(row);
        }
    }
}
