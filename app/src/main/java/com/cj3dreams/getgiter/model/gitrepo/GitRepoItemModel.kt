package com.cj3dreams.getgiter.model.gitrepo

data class GitRepoItemModel(
    val full_name: String,
    val owner: Owner,
    val html_url: String,
    val created_at: String,
    val description: String,
)