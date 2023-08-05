package org.example.detekt

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.rules.KotlinCoreEnvironmentTest
import io.gitlab.arturbosch.detekt.test.compileAndLintWithContext
import io.kotest.matchers.collections.shouldHaveSize
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.junit.jupiter.api.Test

@KotlinCoreEnvironmentTest
internal class BooleanNotOverExclamationTest(private val env: KotlinCoreEnvironment) {

    @Test
    fun `reports ! operator`() {
        val code = """
        class A {
          val a: Boolean = true
          fun method() {
              if(!true) {
              
              }
              if(!a) {
              
              }
              while(!a) {

              }
              method2(!a)
              method2(arg = !a)

              val b = !a

              if(b && !a) {
              
              }

              if(a !is String) {
               
              }
          }

          fun method2(arg: Boolean) {
              
          }
        }
        """
        val findings = BooleanNotOverExclamation(Config.empty).compileAndLintWithContext(env, code)
        findings shouldHaveSize 7
    }

    @Test
    fun `doesn't report 'not' operator`() {
        val code = """
        class A {
          val a: Boolean = true
          fun method() {
              if(true.not()) {
              
              }
              if(a.not()) {
              
              }
              while(a.not()) {

              }
              method2(a.not())
              method2(arg = a.not())

              val b = a.not()

              if(b && a.not()) {
              
              }

              if(a !is String) {
               
              }
          }

          fun method2(arg: Boolean) {
              
          }
        }
        """
        val findings = BooleanNotOverExclamation(Config.empty).compileAndLintWithContext(env, code)
        findings shouldHaveSize 0
    }
}
