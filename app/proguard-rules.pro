# Add project specific ProGuard rules here.

# Supabase / Ktor
-keep class io.github.jan.** { *; }
-keep class io.ktor.** { *; }

# Kotlinx Serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keep,includedescriptorclasses class com.madev.marketplace.**$$serializer { *; }
-keepclassmembers class com.madev.marketplace.** {
    *** Companion;
}
-keepclasseswithmembers class com.madev.marketplace.** {
    kotlinx.serialization.KSerializer serializer(...);
}
-keep class com.madev.marketplace.data.model.** { *; }

# Firebase
-keep class com.google.firebase.** { *; }

# PayHere
-keep class lk.payhere.** { *; }

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {<init>(...);}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {**[] $VALUES; public *;}

# Markwon
-keep class io.noties.markwon.** { *; }

# Keep R class
-keepclassmembers class **.R$* { public static <fields>; }

# Remove logging in release
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int d(...);
}
