package com.duw.exceptions

class NodeNotFoundInLocalDatabase(
    val className: String,
    val key: String
) : Exception("$className $key") {
}