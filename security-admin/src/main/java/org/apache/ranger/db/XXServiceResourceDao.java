/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.ranger.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.lang.StringUtils;
import org.apache.ranger.authorization.utils.StringUtil;
import org.apache.ranger.common.db.BaseDao;
import org.apache.ranger.entity.XXServiceResource;

public class XXServiceResourceDao extends BaseDao<XXServiceResource> {

	public XXServiceResourceDao(RangerDaoManagerBase daoManager) {
		super(daoManager);
	}

	public XXServiceResource findByGuid(String guid) {
		if (StringUtil.isEmpty(guid)) {
			return null;
		}
		try {
			return getEntityManager().createNamedQuery("XXServiceResource.findByGuid", tClass)
					.setParameter("guid", guid).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<XXServiceResource> findByServiceId(Long serviceId) {
		if (serviceId == null) {
			return new ArrayList<XXServiceResource>();
		}
		try {
			return getEntityManager().createNamedQuery("XXServiceResource.findByServiceId", tClass)
					.setParameter("serviceId", serviceId).getResultList();
		} catch (NoResultException e) {
			return new ArrayList<XXServiceResource>();
		}
	}

	public XXServiceResource findByResourceSignature(String resourceSignature) {
		if (StringUtils.isBlank(resourceSignature)) {
			return null;
		}
		try {
			return getEntityManager().createNamedQuery("XXServiceResource.findByResourceSignature", tClass)
					.setParameter("resourceSignature", resourceSignature).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<XXServiceResource> findTaggedResourcesInServiceId(Long serviceId) {
		if (serviceId == null) {
			return new ArrayList<XXServiceResource>();
		}
		try {
			return getEntityManager().createNamedQuery("XXServiceResource.findTaggedResourcesInServiceId", tClass)
					.setParameter("serviceId", serviceId).getResultList();
		} catch (NoResultException e) {
			return new ArrayList<XXServiceResource>();
		}
	}

	public void updateServiceForServiceResourceUpdate(Long resourceId, Date updateTime) {
		if (resourceId == null) {
			return;
		}

		if(updateTime == null) {
			updateTime = new Date();
		}

		try {
			getEntityManager().createNamedQuery("XXServiceResource.updateTagVersionInService", tClass)
					.setParameter("resourceId", resourceId)
					.setParameter("tagUpdateTime", updateTime)
					.executeUpdate();
		} catch (NoResultException e) {
			return;
		}
	}
}
