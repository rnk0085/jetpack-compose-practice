package com.rnk0085.android.jetpackcomposepractice

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

/**
 * https://mockk.io/
 */
class ExampleUnitTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var mockExample: Example

    // https://mockk.io/#relaxed-mock
    @RelaxedMockK
    lateinit var relaxedMockExample: Example

    // Spy の時は lateinit では無いので注意
    @SpyK
    var spyExample = Example()

    /**
     * そもそもテストがエラー出て動かない
     * 「no answer found for: Example(mockExample#1).getA()」
     * -> Mock だとまっさらな状態になっていそう…？
     */
    @Test
    fun test_error_by_mock() {
        val a = mockExample.getA()
        val b = mockExample.getB()
        val c = mockExample.getC()

        assertEquals("A", a)
        assertEquals("B", b)
        assertEquals("C", c)
    }

    /**
     * Mock はテスト時にメソッドの戻り値を設定しないと呼び出しても動かない
     * （RelaxedMockも同様）
     */
    @Test
    fun test_success_by_mock() {
        // メソッドを呼び出してどんな結果が返るかをここでセットしないといけない
        every { mockExample.getA() } returns "A"
        every { mockExample.getB() } returns "BBB"

        val a = mockExample.getA()
        val b = mockExample.getB()

        assertEquals("A", a)
        assertNotEquals("B", b)
        assertEquals("BBB", b)

        // ちゃんと1回ずつ呼び出されているか確認
        verify(exactly = 1) { mockExample.getA() }
        verify(exactly = 1) { mockExample.getB() }

        // 全ての呼び出しが verify で検証されたか確認
        confirmVerified(mockExample)
    }

    /**
     * 失敗する（エラーは出ない）
     * expected:<[A]> but was:<[]>
     *     Expected :A
     *     Actual   :
     *
     * -> RelaxedMock は全ての関数に対して何らかの単純な値を返すモックのことだから、
     *    エラーは起きないものの、Nullが入ってくる
     */
    @Test
    fun test_failure_by_relaxedMock() {
        val a = relaxedMockExample.getA()
        val b = relaxedMockExample.getB()
        val c = relaxedMockExample.getC()

        assertEquals("A", a)
        assertEquals("B", b)
        assertEquals("C", c)
    }

    /**
     * 成功する
     * ＝ Spy は本物のメソッドを呼び出している
     */
    @Test
    fun test_success_by_spy() {
        val a = spyExample.getA()
        val b = spyExample.getB()
        val c = spyExample.getC()

        assertEquals("A", a)
        assertEquals("B", b)
        assertEquals("C", c)

        // ちゃんと1回ずつ呼び出されているか確認
        verify(exactly = 1) { spyExample.getA() }
        verify(exactly = 1) { spyExample.getB() }
        verify(exactly = 1) { spyExample.getC() }

        // 全ての呼び出しが verify で検証されたか確認
        confirmVerified(spyExample)
    }

    @Test
    fun test_success_by_spy_overWrite() {
        // getBだけを書き換えて、それ以外は本物のメソッドをそのまま呼び出したい
        every { spyExample.getB() } returns "BBB"

        val a = spyExample.getA()
        val b = spyExample.getB()
        val c = spyExample.getC()

        assertEquals("A", a)
        assertNotEquals("B", b)
        assertEquals("BBB", b)
        assertEquals("C", c)

        // ちゃんと1回ずつ呼び出されているか確認
        verify(exactly = 1) { spyExample.getA() }
        verify(exactly = 1) { spyExample.getB() }
        verify(exactly = 1) { spyExample.getC() }

        // 全ての呼び出しが verify で検証されたか確認
        confirmVerified(spyExample)
    }
}
