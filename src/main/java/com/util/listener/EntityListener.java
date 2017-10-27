package com.util.listener;

import com.util.entity.BaseEntity;
import com.util.sessiondata.StoredUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class EntityListener {

	public Object getSessionValue(String key) {
		RequestAttributes requestAttributes = RequestContextHolder
				.getRequestAttributes();
		if (requestAttributes != null) {
			HttpSession session = ((ServletRequestAttributes) requestAttributes)
					.getRequest().getSession();
			if (null == session) {
				return null;
			} else {
				Object ret = session.getAttribute(key);
				return ret;
			}
		} else {
			return null;
		}
	}

	public String getUser() {
		StoredUser user = (StoredUser) getSessionValue(StoredUser.KEY);
		return user != null ? user.getName() : "";
	}

	@PrePersist
    void onPrePersist(BaseEntity o) {
		o.setCreationTime(new Date());
		o.setLastUpdateTime(new Date());
		if (StringUtils.isEmpty(o.getCreatedBy())) {
			o.setCreatedBy(getUser());
		}
		if (StringUtils.isEmpty(o.getUpdatedBy())) {
			o.setUpdatedBy(getUser());
		}
	}

	@PostPersist
    void onPostPersist(BaseEntity o) {
	}

	@PostLoad
    void onPostLoad(BaseEntity o) {
	}

	@PreUpdate
    void onPreUpdate(BaseEntity o) {
		o.setLastUpdateTime(new Date());
		if (StringUtils.isNotEmpty(getUser())) {
			o.setUpdatedBy(getUser());
		}
	}

	@PostUpdate
    void onPostUpdate(BaseEntity o) {
	}

	@PreRemove
    void onPreRemove(BaseEntity o) {
	}

	@PostRemove
    void onPostRemove(BaseEntity o) {
	}
}
