package com.b3backoffice.domain.exception

data class DeletedCommentException(
    override val message: String? = "The comment has already been deleted"
):RuntimeException()