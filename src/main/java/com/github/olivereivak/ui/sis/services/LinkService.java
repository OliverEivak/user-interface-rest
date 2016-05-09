package com.github.olivereivak.ui.sis.services;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.olivereivak.ui.sis.dao.LinkDAO;
import com.github.olivereivak.ui.sis.entity.Grade;
import com.github.olivereivak.ui.sis.entity.Link;
import com.github.olivereivak.ui.sis.entity.Role;
import com.github.olivereivak.ui.sis.entity.User;

@Singleton
public class LinkService {

    @Inject
    private LinkDAO linkDAO;

    public Link getByID(Long id, User loggedInUser) {
        Link link = linkDAO.findByID(id);
        if (!loggedInUser.getRole().equals(Role.TEACHER) && !loggedInUser.getId().equals(link.getUser().getId())) {
            throw new RuntimeException("Not allowed to view other student's links");
        }
        return link;
    }

    public Link getByUserAndGrade(User user, Grade grade, User loggedInUser) {
        if (!loggedInUser.getRole().equals(Role.TEACHER) && !loggedInUser.getId().equals(user.getId())) {
            throw new RuntimeException("Not allowed to view other student's links");
        }
        return linkDAO.findByUserAndGrade(user, grade);
    }

    public Link create(Link link, User loggedInUser) {
        if (link.getId() != null) {
            throw new RuntimeException("New link must not have an ID");
        }
        link.setUser(loggedInUser);
        return linkDAO.update(link);
    }

    public Link update(Link link, User loggedInUser) {
        if (link.getId() == null) {
            throw new RuntimeException("Link must have an ID");
        }
        Link originalLink = getByID(link.getId(), loggedInUser);
        originalLink.setUrl(link.getUrl());
        return linkDAO.update(originalLink);
    }

    public void delete(Link link, User loggedInUser) {
        Link originalLink = getByID(link.getId(), loggedInUser);
        linkDAO.remove(originalLink);
    }
}
