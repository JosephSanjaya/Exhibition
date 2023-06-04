package com.joseph.exhibition.core.common.domain.forceupdate

/**
 * Use case interface for retrieving the force update note.
 */
interface ForceUpdateNoteUseCase {
    /**
     * Invokes the force update note retrieval and returns the force update note as a [String].
     *
     * @return The force update note.
     */
    suspend operator fun invoke(): String
}
