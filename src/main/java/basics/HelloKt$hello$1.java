//package basics;
//
//import kotlin.coroutines.jvm.internal.DebugMetadata;
//import org.jetbrains.annotations.Nullable;
//import kotlin.coroutines.Continuation;
//import org.jetbrains.annotations.NotNull;
//import kotlin.Metadata;
//import kotlin.coroutines.jvm.internal.ContinuationImpl;
//
//@Metadata(mv = { 1, 1, 15 }, bv = { 1, 0, 3 }, k = 3, d1 = { "\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0086@" }, d2 = { "hello", "", "name", "", "continuation", "Lkotlin/coroutines/Continuation;" })
//@DebugMetadata(f = "Hello.kt", l = { 9, 12 }, i = { 0, 0, 1, 1 }, s = { "L$0", "L$1", "L$0", "L$1" }, n = { "name", "msg", "name", "msg" }, m = "hello", c = "basics.HelloKt")
//public final class HelloKt$hello$1 extends ContinuationImpl {
//    int label;
//    Object L$0;
//    Object L$1;
//    Object result;
//
//    public HelloKt$hello$1(@Nullable Continuation<Object> completion) {
//        super(completion);
//    }
//
//    @Nullable
//    public final Object invokeSuspend(@NotNull final Object $result) {
//        this.result = $result;
//        this.label |= Integer.MIN_VALUE;
//        return HelloKt.hello(null, (Continuation<? super String>)this);
//    }
//}