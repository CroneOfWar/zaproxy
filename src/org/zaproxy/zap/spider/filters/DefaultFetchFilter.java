/*
 * Zed Attack Proxy (ZAP) and its related class files.
 * 
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package org.zaproxy.zap.spider.filters;

import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;

/**
 * The DefaultFetchFilter is an implementation of a FetchFilter that is default for spidering
 * process. Its filter rules are the following:<br/>
 * <ul>
 * <li>the resource protocol/scheme must be 'http' or 'https'.</li>
 * <li>the resource must be found in the scope (domain) of the spidering process.</li>
 * </ul>
 * 
 */
public class DefaultFetchFilter extends FetchFilter {

	/** The scope. */
	private String scope = "feedrz.com";

	/* (non-Javadoc)
	 * 
	 * @see
	 * org.zaproxy.zap.spider.filters.FetchFilter#checkFilter(org.apache.commons.httpclient.URI) */
	@Override
	public FetchStatus checkFilter(URI uri) {

		// Protocol check
		if (!uri.getScheme().equalsIgnoreCase("http") && !uri.getScheme().equalsIgnoreCase("https"))
			return FetchStatus.ILLEGAL_PROTOCOL;

		// Scope check
		try {
			if (!uri.getHost().endsWith(scope))
				return FetchStatus.OUT_OF_SCOPE;
		} catch (URIException e) {
			log.warn("Error while fetching host for uri: " + uri, e);
			return FetchStatus.OUT_OF_SCOPE;
		}

		return FetchStatus.VALID;
	}

}
