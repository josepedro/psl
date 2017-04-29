/*
 * This file is part of the PSL software.
 * Copyright 2011-2015 University of Maryland
 * Copyright 2013-2015 The Regents of the University of California
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
package org.linqs.psl.reasoner.term;

import java.util.ArrayList;
import java.util.List;

public interface TermStore<E extends Term> extends Iterable<E>{
	public void add(E term);

	/**
	 * Remove any existing terms and prepare for a new set.
	 */
	public void clear();

	/**
	 * Close down the term store, it will not be used any more.
	 */
	public void close();

	public E get(int index);

	public int size();
}
