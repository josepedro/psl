/*
 * This file is part of the PSL software.
 * Copyright 2011 University of Maryland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.umd.cs.psl.database;

import java.util.List;

import edu.umd.cs.psl.database.loading.Inserter;
import edu.umd.cs.psl.database.loading.Updater;
import edu.umd.cs.psl.model.predicate.Predicate;

/**
 * A repository for persisting and querying for {@link AtomRecord AtomRecords}.
 * 
 * A DataStore organizes AtomRecords into {@link Partition Partitions} and
 * makes them available via {@link Database Databases}.
 */
public interface DataStore {

	/**
	 * Registers a Predicate so that {@link AtomRecord AtomRecords} of that Predicate
	 * can be stored in this DataStore.
	 * 
	 * The {@link DataFormat DataFormats} of the arguments will be determined
	 * by {@link DataFormat#getDefaultFormat(Predicate, boolean)}.
	 * 
	 * @param predicate  the predicate to register
	 * @param argnames  names of arguments
	 */
	public void registerPredicate(Predicate predicate, List<String> argnames);
	
	/**
	 * Registers a Predicate so that {@link AtomRecord AtomRecords} of that Predicate
	 * can be stored in this DataStore.
	 * 
	 * @param predicate  the predicate to register
	 * @param argnames  names of arguments
	 * @param formats  the DataFormats to use for the arguments
	 */
	public void registerPredicate(Predicate predicate, List<String> argnames, DataFormat[] formats);
	
	/**
	 * Creates a Database that can read from and write to a {@link Partition} and
	 * additionally read from a set of additional Partitions.
	 * 
	 * @param write  the Partition to write to and read from
	 * @param read  additional Partitions to read from
	 * @return a new Database backed by this DataStore
	 */
	public Database getDatabase(Partition write, Partition... read);
	
	/**
	 * Creates an Inserter for inserting new {@link AtomRecord} information
	 * into a {@link Partition}.
	 * 
	 * @param predicate  the Predicate of the AtomRecords to be inserted
	 * @param partition  the Partition into which AtomRecords will be inserted
	 * @return the Inserter
	 */
	public Inserter getInserter(Predicate predicate, Partition partition);
	
	/**
	 * Creates an Updater for updating {@link AtomRecord} information
	 * in a {@link Partition}.
	 * 
	 * @param predicate  the Predicate of the AtomRecords to be updated
	 * @param partition  the Partition of the AtomRecords to be updated
	 * @return the Updater
	 */
	public Updater getUpdater(Predicate predicate, Partition partition);
	
	/**
	 * Deletes all {@link AtomRecord AtomRecords} in a Partition.
	 *  
	 * @param partition  the partition to delete
	 * @return the number of AtomRecords deleted
	 */
	public int deletePartition(Partition partition);
	
	/**
	 * Releases all resources and locks obtained by this DataStore.
	 */
	public void close();
	
}
