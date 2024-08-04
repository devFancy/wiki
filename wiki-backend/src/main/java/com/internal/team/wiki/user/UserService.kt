package com.internal.team.wiki.user

import com.internal.team.wiki.user.domain.Nickname
import com.internal.team.wiki.user.domain.Password
import com.internal.team.wiki.user.domain.UserEntity
import com.internal.team.wiki.user.domain.Username
import com.internal.team.wiki.user.domain.hashing.HashingI
import com.internal.team.wiki.user.dto.UserSignupRequest
import com.internal.team.wiki.user.exception.InvalidNicknameException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService (
   private val userRepository: UserRepository,
   private val hashingI: HashingI
) {

   @Transactional
   fun signup(request: UserSignupRequest): Long {
      if (userRepository.existsByNicknameValue(request.nickname)) {
         throw InvalidNicknameException("닉네임이 중복되었습니다.")
      }

      val user = UserEntity(
         username = Username(request.username),
         nickname = Nickname(request.nickname),
         password = Password.of(hashingI, request.password),
      )
      userRepository.save(user)

      return user.id ?: throw IllegalStateException("User ID should not be null after saving")
   }

}