package by.bashlikovvv.heroes_compose.data

import java.lang.RuntimeException

sealed class AppException : RuntimeException()

class DetailsNotFoundException : AppException()