package com.instagram.controller;

import com.instagram.model.Follow;
import com.instagram.model.Users;
import com.instagram.service.*;

import java.util.List;
import java.util.Scanner;

public class MainController {

    public static void main(String[] args) {

        // Create Service objects
        UserService userService = new UserServiceImpl();
        ProfileService profileService = new ProfileServiceImpl();
        PostService postService = new PostServiceImpl();
        CommentService commentService = new CommentServiceImpl();
        LikeService likeService = new LikeServiceImpl();
        FollowService followService = new FollowServiceImpl();
        AdminService adminService = new AdminServiceImpl();

        // Create Controller objects
        UserController userController =
                new UserController(userService);

        ProfileController profileController =
                new ProfileController(profileService);

        PostController postController =
                new PostController(postService);

        CommentController commentController =
                new CommentController(commentService);

        LikeController likeController =
                new LikeController(likeService);

        FollowController followController =
                new FollowController(followService);

        AdminController adminController =
                new AdminController(adminService);

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== Instagram Application =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();

                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    Users user = new Users();

                    user.setUsername(username);
                    user.setEmail(email);
                    user.setPasswordHash(password);
                    user.setRole("USER");
                    user.setStatus("ACTIVE");

                    boolean registered =
                            userController.registerUser(user);

                    if (registered) {
                        System.out.println("Registration successful!");
                    } else {
                        System.out.println("Registration failed!");
                    }

                    break;

                case 2:

                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();

                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();

                    Users loggedInUser =
                            userController.login(
                                    loginUsername,
                                    loginPassword
                            );

//                    if (loggedInUser == null) {
//                        System.out.println("Invalid username or password!");
//                        break;
//                    }
//
//                    if ("INACTIVE".equals(loggedInUser.getStatus())) {
//                        System.out.println("Your account is inactive.");
//                        break;
//                    }
//
//                    System.out.println(
//                            "Login successful! Welcome, "
//                                    + loggedInUser.getUsername()
//
//                    );
//
//                    userMenu(
//                            scanner,
//                            loggedInUser,
//                            profileController,
//                            postController,
//                            commentController,
//                            likeController,
//                            followController
//                    );


                    if (loggedInUser == null) {

                        System.out.println("Invalid username or password.");

                    } else if ("INACTIVE".equals(loggedInUser.getStatus())) {

                        System.out.println("Your account is inactive.");
                        break;

                    } else {

                        System.out.println("Login successful!");

                        if ("ADMIN".equals(loggedInUser.getRole())) {

                            adminMenu(scanner, adminController);

                        } else {

                            userMenu(
                                    scanner,
                                    loggedInUser,
                                    userController,
                                    profileController,
                                    postController,
                                    commentController,
                                    likeController,
                                    followController
                            );
                        }
                    }
                    break;

                case 3:

                    System.out.println("Application closed.");
                    scanner.close();
                    return;

                default:

                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void adminMenu(
            Scanner scanner,
            AdminController adminController) {

        while (true) {

            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. View Dashboard");
            System.out.println("2. View All Users");
            System.out.println("3. Search User");
            System.out.println("4. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    int totalUsers =
                            adminController.getTotalUsers();
                    int totalPosts =
                            adminController.getTotalPosts();

                   // int totalFollowers =
                     //       adminController.getTotalFollowers();

                    //int totalFollowing =
                     //       adminController.getTotalFollowing();

                    int totalLikes =
                            adminController.getTotalLikes();

                    System.out.println();

                    System.out.println(
                            "╔══════════════════════════════════════════════╗"
                    );

                    System.out.println(
                            "║               ADMIN DASHBOARD                ║"
                    );

                    System.out.println(
                            "╠══════════════════════════════════════════════╣"
                    );

                    System.out.println(
                            "║                                              ║"
                    );

                    System.out.println(
                            "║  Total Users      : "
                                    + fitText(
                                    String.valueOf(totalUsers),
                                    20
                            )
                                    + "     ║"
                    );
                    System.out.println(
                            "║  Total Posts      : "
                                    + fitText(
                                    String.valueOf(totalUsers),
                                    20
                            )
                                    + "     ║"
                    );

//                    System.out.println(
//                            "║  Total Followers  : "
//                                    + fitText(
//                                    String.valueOf(totalFollowers),
//                                    20
//                            )
//                                    + "║"
//                    );

//                    System.out.println(
//                            "║  Total Following  : "
//                                    + fitText(
//                                    String.valueOf(totalFollowing),
//                                    20
//                            )
//                                    + "║"
//                    );

//                    System.out.println(
//                            "║  Total Likes      : "
//                                    + fitText(
//                                    String.valueOf(totalLikes),
//                                    20
//                            )
//                                    + "║
//                    );

                    System.out.println(
                            "║                                              ║"
                    );

                    System.out.println(
                            "╚══════════════════════════════════════════════╝"
                    );

                    break;

                case 2:

                    List<Users> users = adminController.getAllUsers();

                    System.out.println();
                    System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
                    System.out.println("║                     ALL USERS                                                ║");
                    System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");

                    if (users.isEmpty()) {

                        System.out.println("║  No users found.                                           ║");

                    } else {

                        for (Users user : users) {

                            System.out.println(
                                    "║  ID: " + fitText(String.valueOf(user.getUserId()), 5)
                                            + " Username: "
                                            + fitText(user.getUsername(), 20)
                                            + " Role: "
                                            + fitText(user.getRole(), 8)
                                            + " Status: "
                                            + fitText(user.getStatus(), 10)
                                            + "  ║"
                            );
                        }
                    }

                    System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");

                    break;

                case 3:

                    System.out.print("Enter username to search: ");
                    String searchUsername = scanner.nextLine();

                    List<Users> searchResults =
                            adminController.searchUsers(searchUsername);

                    System.out.println();
                    System.out.println("╔════════════════════════════════════════════════════╗");
                    System.out.println("║                   SEARCH RESULTS                  ║");
                    System.out.println("╠════════════════════════════════════════════════════╣");

                    if (searchResults.isEmpty()) {

                        System.out.println("║  No users found.                                   ║");

                    } else {

                        for (Users user : searchResults) {

                            System.out.println(
                                    "║  ID: " +
                                            fitText(String.valueOf(user.getUserId()), 5) +
                                            " Username: " +
                                            fitText(user.getUsername(), 20) +
                                            " Status: " +
                                            fitText(user.getStatus(), 10) +
                                            "║"
                            );
                        }
                    }

                    System.out.println("╚════════════════════════════════════════════════════╝");

                    break;
                case 4:

                    System.out.println(
                            "Admin logged out successfully."
                    );

                    return;

                default:

                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void userMenu(
            Scanner scanner,
            Users loggedInUser,
             UserController userController,
            ProfileController profileController,
            PostController postController,
            CommentController commentController,
            LikeController likeController,
            FollowController followController) {

        while (true) {

            System.out.println("\n===== User Menu =====");

            System.out.println("1. Create Profile");
            System.out.println("2. View Profile");
            System.out.println("3. Edit Profile");
            System.out.println("4. Delete Profile");
            System.out.println("5. Create Post");
            System.out.println("6. View My Posts");
            System.out.println("7. Edit Post");
            System.out.println("8. Delete Post");
            System.out.println("9. Add Comment");
            System.out.println("10. View Post Comments");
            System.out.println("11. Like Post");
            System.out.println("12. Unlike Post");
            System.out.println("13. View Like Count");
            System.out.println("14. Follow User");
            System.out.println("15. Unfollow User");
            System.out.println("16. View Followers");
            System.out.println("17. View Following");
            System.out.println("18.  Search User");
            System.out.println("19.  View All Posts");
            System.out.println("20. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter profile name: ");
                    String profileName = scanner.nextLine();

                    System.out.print("Enter bio: ");
                    String bio = scanner.nextLine();

                    System.out.print("Enter phone: ");
                    String phone = scanner.nextLine();

                    System.out.print("Enter profile image URL (optional): ");
                    String profileImageUrl = scanner.nextLine();

                    com.instagram.model.Profile profile =
                            new com.instagram.model.Profile();

                    profile.setUserId(loggedInUser.getUserId());
                    profile.setProfileName(profileName);
                    profile.setBio(bio);
                    profile.setPhone(phone);
                    profile.setProfileImageUrl(profileImageUrl);

                    if (profileController.addProfile(profile)) {
                        System.out.println("Profile created successfully!");
                    } else {
                        System.out.println("Failed to create profile.");
                    }

                    break;

                case 2:

                    com.instagram.model.Profile profiles =
                            profileController.getProfileByUserId(
                                    loggedInUser.getUserId()
                            );

                    java.util.List<com.instagram.model.Post> posts =
                            postController.getPostsByUserId(
                                    loggedInUser.getUserId()
                            );

                    int followers =
                            followController.getFollowerCount(
                                    loggedInUser.getUserId()
                            );

                    int following =
                            followController.getFollowingCount(
                                    loggedInUser.getUserId()
                            );

                    System.out.println();
                    System.out.println("╔══════════════════════════════════════════════╗");
                    System.out.println("║                 USER PROFILE                 ║");
                    System.out.println("╠══════════════════════════════════════════════╣");

                    System.out.println("║  @" +
                            fitText(loggedInUser.getUsername(), 39) + "║");

                    System.out.println("║                                              ║");

                    System.out.println("║  User ID: " +
                            fitText(String.valueOf(loggedInUser.getUserId()), 32) + "   ║");

                    if (profiles != null) {

                        System.out.println("║  Name: " +
                                fitText(profiles.getProfileName(), 35) + "     ║");

                        System.out.println("║  Bio: " +
                                fitText(profiles.getBio(), 36) + "        ║");

                        System.out.println("║  Image: " +
                                fitText(profiles.getProfileImageUrl(), 33) + "   ║");

                    } else {

                        System.out.println("║  Profile: Not created yet                   ║");
                    }

                    System.out.println("║                                              ║");

                    System.out.println("║  Followers: " +
                            fitText(String.valueOf(followers), 7) +
                            " Following: " +
                            fitText(String.valueOf(following), 7) +
                            " Posts: " +
                            fitText(String.valueOf(posts.size()), 6) + "║");

                    System.out.println("╠══════════════════════════════════════════════╣");
                    System.out.println("║                    POSTS                     ║");
                    System.out.println("╠══════════════════════════════════════════════╣");

                    if (posts.isEmpty()) {

                        System.out.println("║  No posts available.                         ║");

                    } else {

                        for (com.instagram.model.Post post : posts) {

                            int likeCount =
                                    likeController.getLikeCount(
                                            post.getPostId()
                                    );

                            java.util.List<com.instagram.model.Comment> comments =
                                    commentController.getCommentsByPostId(
                                            post.getPostId()
                                    );

                            System.out.println("║                                              ║");

                            System.out.println("║  Post #" +
                                    fitText(
                                            String.valueOf(post.getPostId()),
                                            34
                                    ) + "║");

                            System.out.println("║  Caption: " +
                                    fitText(post.getCaption(), 31) + "║");

                            System.out.println("║  Image: " +
                                    fitText(post.getImageUrl(), 33) + "║");

                            System.out.println("║  Likes: " +
                                    fitText(String.valueOf(likeCount), 34) + "║");

                            System.out.println("║                                              ║");

                            System.out.println("║  Comments:                                   ║");

                            if (comments.isEmpty()) {

                                System.out.println("║  No comments yet.                            ║");

                            } else {

                                for (com.instagram.model.Comment comment : comments) {

                                    System.out.println(
                                            "║  User " +
                                                    fitText(
                                                            String.valueOf(comment.getUserId()),
                                                            5
                                                    ) +
                                                    ": " +
                                                    fitText(
                                                            comment.getCommentText(),
                                                            30
                                                    ) +
                                                    "║"
                                    );
                                }
                            }
                        }
                    }

                    System.out.println("╚══════════════════════════════════════════════╝");

                    break;

                case 3:
                    com.instagram.model.Profile currentProfile =
                        profileController.getProfileByUserId(
                                loggedInUser.getUserId()
                        );

                if (currentProfile == null) {
                    System.out.println("Profile not found. Create a profile first.");
                    break;
                }

                System.out.print("Enter new profile name: ");
                currentProfile.setProfileName(scanner.nextLine());

                System.out.print("Enter new bio: ");
                currentProfile.setBio(scanner.nextLine());

                System.out.print("Enter new phone: ");
                currentProfile.setPhone(scanner.nextLine());

                System.out.print("Enter new profile image URL: ");
                currentProfile.setProfileImageUrl(scanner.nextLine());

                if (profileController.updateProfile(currentProfile)) {
                    System.out.println("Profile updated successfully!");
                } else {
                    System.out.println("Failed to update profile.");
                }

                break;


                case 4:

                    com.instagram.model.Profile profileToDelete =
                            profileController.getProfileByUserId(
                                    loggedInUser.getUserId()
                            );

                    if (profileToDelete == null) {
                        System.out.println("Profile not found.");
                        break;
                    }

                    System.out.print("Are you sure you want to delete your profile? (yes/no): ");
                    String confirmation = scanner.nextLine();

                    if (confirmation.equalsIgnoreCase("yes")) {

                        if (profileController.deleteProfile(
                                profileToDelete.getProfileId())) {

                            System.out.println("Profile deleted successfully!");

                        } else {
                            System.out.println("Failed to delete profile.");
                        }

                    } else {
                        System.out.println("Profile deletion cancelled.");
                    }

                    break;

                case 5:

                    System.out.print("Enter caption: ");
                    String caption = scanner.nextLine();

                    System.out.print("Enter image URL (optional): ");
                    String imageUrl = scanner.nextLine();

                    com.instagram.model.Post post =
                            new com.instagram.model.Post();

                    post.setUserId(loggedInUser.getUserId());
                    post.setCaption(caption);
                    post.setImageUrl(imageUrl);

                    if (postController.addPost(post)) {
                        System.out.println("Post created successfully!");
                    } else {
                        System.out.println("Failed to create post.");
                    }

                    break;

                case 6:
                    System.out.println("My Post is:");
                    System.out.println(
                            postController.getPostsByUserId(
                                    loggedInUser.getUserId()
                            )
                    );

                    break;

                case 7:

                    System.out.print("Enter post ID to edit: ");
                    int postId = scanner.nextInt();
                    scanner.nextLine();

                    com.instagram.model.Post postToUpdate =
                            postController.getPostById(postId);

                    if (postToUpdate == null) {
                        System.out.println("Post not found.");
                        break;
                    }

                    // Make sure the logged-in user owns the post
                    if (postToUpdate.getUserId() != loggedInUser.getUserId()) {
                        System.out.println("You can only edit your own posts.");
                        break;
                    }

                    System.out.print("Enter new caption: ");
                    String newCaption = scanner.nextLine();

                    System.out.print("Enter new image URL (optional): ");
                    String newImageUrl = scanner.nextLine();

                    postToUpdate.setCaption(newCaption);
                    postToUpdate.setImageUrl(newImageUrl);

                    if (postController.updatePost(postToUpdate)) {
                        System.out.println("Post updated successfully!");
                    } else {
                        System.out.println("Failed to update post.");
                    }

                    break;


                case 8:

                    System.out.print("Enter post ID to delete: ");
                    int deletePostId = scanner.nextInt();
                    scanner.nextLine();

                    com.instagram.model.Post postToDelete =
                            postController.getPostById(deletePostId);

                    if (postToDelete == null) {
                        System.out.println("Post not found.");
                        break;
                    }

                    // Make sure the logged-in user owns the post
                    if (postToDelete.getUserId() != loggedInUser.getUserId()) {
                        System.out.println("You can only delete your own posts.");
                        break;
                    }

                    System.out.print("Are you sure you want to delete this post? (yes/no): ");
                    String D_confirmation = scanner.nextLine();

                    if (D_confirmation.equalsIgnoreCase("yes")) {

                        if (postController.deletePost(deletePostId)) {
                            System.out.println("Post deleted successfully!");
                        } else {
                            System.out.println("Failed to delete post.");
                        }

                    } else {
                        System.out.println("Post deletion cancelled.");
                    }

                    break;

                case 9:

                    System.out.print("Enter post ID: ");
                    int post_Id = scanner.nextInt();
                    scanner.nextLine();

                    // Check whether the post exists
                    com.instagram.model.Post Apost =
                            postController.getPostById(post_Id);

                    if (Apost == null) {
                        System.out.println("Post not found.");
                        break;
                    }

                    System.out.print("Enter your comment: ");
                    String commentText = scanner.nextLine();

                    com.instagram.model.Comment comment =
                            new com.instagram.model.Comment();

                    comment.setUserId(loggedInUser.getUserId());
                    comment.setPostId(post_Id);
                    comment.setParentCommentId(null);
                    comment.setCommentText(commentText);

                    if (commentController.addComment(comment)) {
                        System.out.println("Comment added successfully!");
                    } else {
                        System.out.println("Failed to add comment.");
                    }

                    break;

                case 10:

                    System.out.print("Enter post ID: ");
                    int commentPostId = scanner.nextInt();
                    scanner.nextLine();

                    com.instagram.model.Post selectedPost =
                            postController.getPostById(commentPostId);

                    if (selectedPost == null) {
                        System.out.println("Post not found.");
                        break;
                    }

                    java.util.List<com.instagram.model.Comment> comments =
                            commentController.getCommentsByPostId(commentPostId);

                    System.out.println();
                    System.out.println("╔══════════════════════════════════════════════╗");
                    System.out.println("║                POST COMMENTS                 ║");
                    System.out.println("╠══════════════════════════════════════════════╣");

                    if (comments.isEmpty()) {

                        System.out.println("║  No comments yet.                            ║");

                    } else {

                        for (com.instagram.model.Comment c : comments) {

                            System.out.println("║                                                ║");

                            System.out.println("║  Comment ID : " +
                                    fitText(String.valueOf(c.getCommentId()), 29) + "  ║");

                            System.out.println("║  User ID    : " +
                                    fitText(String.valueOf(c.getUserId()), 29) + "  ║");

                            System.out.println("║  Comment    : " +
                                    fitText(c.getCommentText(), 29) + "║");
                        }
                    }

                    System.out.println("╚══════════════════════════════════════════════╝");

                    break;


                case 11:

                    System.out.print("Enter post ID: ");
                    int likePostId = scanner.nextInt();
                    scanner.nextLine();

                    com.instagram.model.Post likePost =
                            postController.getPostById(likePostId);

                    if (likePost == null) {
                        System.out.println("Post not found.");
                        break;
                    }

                    com.instagram.model.Like like =
                            new com.instagram.model.Like();

                    like.setUserId(loggedInUser.getUserId());
                    like.setPostId(likePostId);

                    if (likeController.addLike(like)) {
                        System.out.println("Post liked successfully!");
                    } else {
                        System.out.println("You have already liked this post.");
                    }

                    break;

                case 12:

                    System.out.print("Enter post ID: ");
                    int unlikePostId = scanner.nextInt();
                    scanner.nextLine();

                    if (likeController.removeLike(
                            loggedInUser.getUserId(),
                            unlikePostId)) {

                        System.out.println("Post unliked successfully!");

                    } else {

                        System.out.println("Like not found.");
                    }

                    break;

                case 13:

                    System.out.print("Enter post ID: ");
                    int countPostId = scanner.nextInt();
                    scanner.nextLine();

                    com.instagram.model.Post countPost =
                            postController.getPostById(countPostId);

                    if (countPost == null) {
                        System.out.println("Post not found.");
                        break;
                    }

                    int likeCount =
                            likeController.getLikeCount(countPostId);

                    System.out.println(
                            "Post " + countPostId +
                                    " has " + likeCount + " like(s)."
                    );

                    break;


                case 14:

                    System.out.print("Enter user ID to follow: ");
                    int followingId = scanner.nextInt();
                    scanner.nextLine();

                    Users userToFollow =
                            userController.getAllUsers()
                                    .stream()
                                    .filter(user -> user.getUserId() == followingId)
                                    .findFirst()
                                    .orElse(null);

                    if (userToFollow == null) {
                        System.out.println("User not found.");
                        break;
                    }

                    Follow follow = new Follow();

                    follow.setFollowerId(loggedInUser.getUserId());
                    follow.setFollowingId(followingId);

                    if (followController.addFollow(follow)) {
                        System.out.println(
                                "You are now following "
                                        + userToFollow.getUsername()
                        );
                    } else {
                        System.out.println(
                                "Unable to follow user. " +
                                        "You may already be following this user."
                        );
                    }

                    break;

                case 15:

                    System.out.print("Enter user ID to unfollow: ");
                    int unfollowId = scanner.nextInt();
                    scanner.nextLine();

                    if (followController.removeFollow(
                            loggedInUser.getUserId(),
                            unfollowId)) {

                        System.out.println("User unfollowed successfully!");

                    } else {

                        System.out.println("You are not following this user.");
                    }

                    break;

                case 16:

                    java.util.List<Follow> V_followers =
                            followController.getFollowers(
                                    loggedInUser.getUserId()
                            );

                    System.out.println();
                    System.out.println("╔══════════════════════════════════════════════╗");
                    System.out.println("║                  FOLLOWERS                   ║");
                    System.out.println("╠══════════════════════════════════════════════╣");

                    if (V_followers.isEmpty()) {

                        System.out.println("║  No followers yet.                           ║");

                    } else {

                        for (Follow follower : V_followers) {

                            Users vfollowers =
                                    userController.getAllUsers()
                                            .stream()
                                            .filter(user ->
                                                    user.getUserId()
                                                            == follower.getFollowerId())
                                            .findFirst()
                                            .orElse(null);

                            if (follower != null) {

                                System.out.println(
                                        "║  " +
                                                fitText(
                                                        vfollowers.getUsername(),
                                                        40
                                                ) +
                                                "║"
                                );
                            }
                        }
                    }

                    System.out.println("╚══════════════════════════════════════════════╝");

                    break;


                case 17:

                    java.util.List<Follow> followings =
                            followController.getFollowing(
                                    loggedInUser.getUserId()
                            );

                    System.out.println();
                    System.out.println("╔══════════════════════════════════════════════╗");
                    System.out.println("║                  FOLLOWING                   ║");
                    System.out.println("╠══════════════════════════════════════════════╣");

                    if (followings.isEmpty()) {

                        System.out.println("║  You are not following anyone yet.           ║");

                    } else {

                        for (Follow follows : followings) {

                            Users followingUser =
                                    userController.getAllUsers()
                                            .stream()
                                            .filter(user ->
                                                    user.getUserId()
                                                            == follows.getFollowingId())
                                            .findFirst()
                                            .orElse(null);

                            if (followingUser != null) {

                                System.out.println(
                                        "║  " +
                                                fitText(
                                                        followingUser.getUsername(),
                                                        40
                                                ) +
                                                "    ║"
                                );
                            }
                        }
                    }

                    System.out.println("╚══════════════════════════════════════════════╝");

                    break;

                case 18:

                    System.out.print("Enter username to search: ");
                    String searchUsername = scanner.nextLine();

                    java.util.List<com.instagram.model.Users> searchResults =
                            userController.getAllUsers()
                                    .stream()
                                    .filter(user ->
                                            user.getUsername()
                                                    .toLowerCase()
                                                    .contains(searchUsername.toLowerCase()))
                                    .toList();

                    System.out.println();
                    System.out.println("╔══════════════════════════════════════════════╗");
                    System.out.println("║                 SEARCH USERS                ║");
                    System.out.println("╠══════════════════════════════════════════════╣");

                    if (searchResults.isEmpty()) {

                        System.out.println("║  No users found.                             ║");
                        System.out.println("╚══════════════════════════════════════════════╝");
                        break;
                    }

                    for (com.instagram.model.Users user : searchResults) {

                        System.out.println(
                                "║  ID: " +
                                        fitText(String.valueOf(user.getUserId()), 5) +
                                        " Username: " +
                                        fitText(user.getUsername(), 25) +
                                        "║"
                        );
                    }

                    System.out.println("╚══════════════════════════════════════════════╝");

                    System.out.print("Enter User ID to view profile: ");
                    int selectedUserId = scanner.nextInt();
                    scanner.nextLine();

                    com.instagram.model.Users selectedUser =
                            searchResults.stream()
                                    .filter(user -> user.getUserId() == selectedUserId)
                                    .findFirst()
                                    .orElse(null);

                    if (selectedUser == null) {
                        System.out.println("Invalid User ID.");
                        break;
                    }

                    com.instagram.model.Profile selectedProfile =
                            profileController.getProfileByUserId(selectedUserId);

                    java.util.List<com.instagram.model.Post> selectedPosts =
                            postController.getPostsByUserId(selectedUserId);

                    int selectedFollowers =
                            followController.getFollowerCount(selectedUserId);

                    int selectedFollowing =
                            followController.getFollowingCount(selectedUserId);

                    System.out.println();
                    System.out.println("╔══════════════════════════════════════════════╗");
                    System.out.println("║              USER PROFILE                   ║");
                    System.out.println("╠══════════════════════════════════════════════╣");

                    System.out.println("║  @" +
                            fitText(selectedUser.getUsername(), 39) + "║");

                    System.out.println("║                                              ║");

                    System.out.println("║  User ID: " +
                            fitText(String.valueOf(selectedUser.getUserId()), 32) + "   ║");

                    if (selectedProfile != null) {

                        System.out.println("║  Name: " +
                                fitText(selectedProfile.getProfileName(), 35) + "     ║");

                        System.out.println("║  Bio: " +
                                fitText(selectedProfile.getBio(), 36) + "        ║");

                        System.out.println("║  Image: " +
                                fitText(selectedProfile.getProfileImageUrl(), 33) + "   ║");

                    } else {

                        System.out.println("║  Profile: Not created yet                   ║");
                    }

                    System.out.println("║                                              ║");

                    System.out.println("║  Followers: " +
                            fitText(String.valueOf(selectedFollowers), 7) +
                            " Following: " +
                            fitText(String.valueOf(selectedFollowing), 7) +
                            " Posts: " +
                            fitText(String.valueOf(selectedPosts.size()), 6) + "║");

                    System.out.println("╠══════════════════════════════════════════════╣");
                    System.out.println("║                    POSTS                     ║");
                    System.out.println("╠══════════════════════════════════════════════╣");

                    if (selectedPosts.isEmpty()) {

                        System.out.println("║  No posts available.                         ║");

                    } else {

                        for (com.instagram.model.Post u_post : selectedPosts) {

                            int u_likeCount =
                                    likeController.getLikeCount(u_post.getPostId());

                            java.util.List<com.instagram.model.Comment> u_comments =
                                    commentController.getCommentsByPostId(
                                            u_post.getPostId()
                                    );

                            System.out.println("║                                              ║");

                            System.out.println("║  Post #" +
                                    fitText(
                                            String.valueOf(u_post.getPostId()),
                                            34
                                    ) + "║");

                            System.out.println("║  Caption: " +
                                    fitText(u_post.getCaption(), 31) + "║");

                            System.out.println("║  Image: " +
                                    fitText(u_post.getImageUrl(), 33) + "║");

                            System.out.println("║  Likes: " +
                                    fitText(String.valueOf(u_likeCount), 34) + "║");

                            System.out.println("║  Comments:                                   ║");

                            if (u_comments.isEmpty()) {

                                System.out.println("║  No comments yet.                            ║");

                            } else {

                                for (com.instagram.model.Comment u_comment : u_comments) {

                                    System.out.println(
                                            "║  User " +
                                                    fitText(
                                                            String.valueOf(u_comment.getUserId()),
                                                            5
                                                    ) +
                                                    ": " +
                                                    fitText(
                                                            u_comment.getCommentText(),
                                                            30
                                                    ) +
                                                    "║"
                                    );
                                }
                            }
                        }
                    }

                    System.out.println("╚══════════════════════════════════════════════╝");

                    break;

                case 19:

                    java.util.List<com.instagram.model.Post> allPosts =
                            postController.getAllPosts();

                    System.out.println();
                    System.out.println("╔══════════════════════════════════════════════╗");
                    System.out.println("║                 ALL POSTS                   ║");
                    System.out.println("╠══════════════════════════════════════════════╣");

                    if (allPosts.isEmpty()) {

                        System.out.println("║  No posts available.                         ║");

                    } else {

                        for (com.instagram.model.Post allPost : allPosts) {

                            Users postUser =
                                    userController.getAllUsers()
                                            .stream()
                                            .filter(user ->
                                                    user.getUserId() == allPost.getUserId())
                                            .findFirst()
                                            .orElse(null);

                            int allLikeCount =
                                    likeController.getLikeCount(
                                            allPost.getPostId()
                                    );

                            java.util.List<com.instagram.model.Comment> allComments =
                                    commentController.getCommentsByPostId(
                                            allPost.getPostId()
                                    );

                            System.out.println("║                                              ║");

                            System.out.println("║  User: " +
                                    fitText(
                                            postUser != null
                                                    ? postUser.getUsername()
                                                    : "Unknown",
                                            34
                                    ) + "║");

                            System.out.println("║  Post #" +
                                    fitText(
                                            String.valueOf(allPost.getPostId()),
                                            34
                                    ) + "║");

                            System.out.println("║  Caption: " +
                                    fitText(allPost.getCaption(), 31) + "║");

                            System.out.println("║  Image: " +
                                    fitText(allPost.getImageUrl(), 33) + "║");

                            System.out.println("║  Likes: " +
                                    fitText(
                                            String.valueOf(allLikeCount),
                                            34
                                    ) + "║");

                            System.out.println("║  Comments:                                   ║");

                            if (allComments.isEmpty()) {

                                System.out.println("║  No comments yet.                            ║");

                            } else {

                                for (com.instagram.model.Comment allComment : allComments) {

                                    System.out.println(
                                            "║  User " +
                                                    fitText(
                                                            String.valueOf(
                                                                    allComment.getUserId()
                                                            ),
                                                            5
                                                    ) +
                                                    ": " +
                                                    fitText(
                                                            allComment.getCommentText(),
                                                            30
                                                    ) +
                                                    "║"
                                    );
                                }
                            }
                        }
                    }

                    System.out.println("╚══════════════════════════════════════════════╝");

                    break;

                case 20:

                    System.out.println("Logged out successfully.");
                    return;


                default:

                    System.out.println("Invalid choice!");
            }
        }
    }


    private static String fitText(String text, int length) {

        if (text == null) {
            text = "";
        }

        if (text.length() > length) {
            return text.substring(0, length - 3) + "...";
        }

        return String.format("%-" + length + "s", text);
    }
}