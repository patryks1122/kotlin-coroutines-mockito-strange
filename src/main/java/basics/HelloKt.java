//package basics;
//
//import org.jetbrains.annotations.Nullable;
//import kotlinx.coroutines.DelayKt;
//import kotlin.ResultKt;
//import kotlin.coroutines.intrinsics.IntrinsicsKt;
//import kotlin.coroutines.Continuation;
//import org.jetbrains.annotations.NotNull;
//import kotlin.Metadata;
//
//@Metadata(mv = { 1, 1, 15 }, bv = { 1, 0, 3 }, k = 2, d1 = { "\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u001b\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0086@\u00f8\u0001\u0000¢\u0006\u0002\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0004" }, d2 = { "hello", "", "name", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "untitled" })
//public final class HelloKt
//{
//    @Nullable
//    public static final Object hello(@NotNull String name, @NotNull final Continuation<? super String> $completion) {
//        final Continuation $continuation;
//        Label_0049: {
//            if ($completion instanceof HelloKt$hello$1) {
//                final HelloKt$hello$1 helloKt$hello$1 = (HelloKt$hello$1)$completion;
//                if ((helloKt$hello$1.label & Integer.MIN_VALUE) != 0x0) {
//                    final HelloKt$hello$1 helloKt$hello$2 = helloKt$hello$1;
//                    helloKt$hello$2.label -= Integer.MIN_VALUE;
//                    break Label_0049;
//                }
//            }
//            $continuation = (Continuation)new HelloKt$hello$1((Continuation)$completion);
//        }
//        final Object coroutine_SUSPENDED = IntrinsicsKt.getCOROUTINE_SUSPENDED();
//        final Object $result = ((HelloKt$hello$1)$continuation).result;
//        String msg = null;
//        Label_0285: {
//            switch (((HelloKt$hello$1)$continuation).label) {
//                case 0: {
//                    ResultKt.throwOnFailure($result);
//                    msg = "Hello";
//                    System.out.print((Object)msg);
//                    final long n = 1000L;
//                    final Continuation continuation = $continuation;
//                    ((HelloKt$hello$1)$continuation).L$0 = name;
//                    ((HelloKt$hello$1)$continuation).L$1 = msg;
//                    ((HelloKt$hello$1)$continuation).label = 1;
//                    if (DelayKt.delay(n, continuation) == coroutine_SUSPENDED) {
//                        return coroutine_SUSPENDED;
//                    }
//                    break;
//                }
//                case 1: {
//                    msg = (String)((HelloKt$hello$1)$continuation).L$1;
//                    name = (String)((HelloKt$hello$1)$continuation).L$0;
//                    ResultKt.throwOnFailure($result);
//                    break;
//                }
//                case 2: {
//                    msg = (String)((HelloKt$hello$1)$continuation).L$1;
//                    name = (String)((HelloKt$hello$1)$continuation).L$0;
//                    ResultKt.throwOnFailure($result);
//                    break Label_0285;
//                }
//                default: {
//                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
//                }
//            }
//            msg = msg + ' ' + name;
//            System.out.print((Object)(' ' + name));
//            final long n2 = 500L;
//            final Continuation continuation2 = $continuation;
//            ((HelloKt$hello$1)$continuation).L$0 = name;
//            ((HelloKt$hello$1)$continuation).L$1 = msg;
//            ((HelloKt$hello$1)$continuation).label = 2;
//            if (DelayKt.delay(n2, continuation2) == coroutine_SUSPENDED) {
//                return coroutine_SUSPENDED;
//            }
//        }
//        msg += "!";
//        System.out.println((Object)"!");
//        return msg;
//    }
//}