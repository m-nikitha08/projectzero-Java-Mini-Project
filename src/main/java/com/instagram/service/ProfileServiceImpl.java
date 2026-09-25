package com.instagram.service;

import com.instagram.dao.ProfileDAO;
import com.instagram.dao.ProfileDAOImpl;
import com.instagram.model.Profile;

import java.util.List;

public class ProfileServiceImpl implements ProfileService {

    private ProfileDAO profileDAO;

    public ProfileServiceImpl() {
        this.profileDAO = new ProfileDAOImpl();
    }


    @Override
    public boolean createProfile(Profile profile) {
        // TODO: Add validation and call ProfileDAO
        return profileDAO.createProfile(profile);
    }

    @Override
    public Profile getProfileById(int profileId) {
        // TODO: Call ProfileDAO
        return profileDAO.getProfileById(profileId);
    }

    @Override
    public Profile getProfileByUserId(int userId) {
        // TODO: Call ProfileDAO
        return profileDAO.getProfileByUserId(userId);
    }

    @Override
    public boolean updateProfile(Profile profile) {
        // TODO: Add validation and call ProfileDAO
        return profileDAO.updateProfile(profile);
    }

    @Override
    public boolean deleteProfile(int profileId) {
        // TODO: Call ProfileDAO
        return profileDAO.deleteProfile(profileId);
    }

    @Override
    public List<Profile> getAllProfiles() {
        return profileDAO.getAllProfiles();
    }
}