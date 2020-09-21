#import "ImagesOverly.h"
#import <UIKit/UIKit.h>
@implementation ImagesOverly

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(sampleMethod:(NSString *)stringArgument numberParameter:(nonnull NSNumber *)numberArgument callback:(RCTResponseSenderBlock)callback)
{
    // TODO: Implement some actually useful functionality
    callback(@[[NSString stringWithFormat: @"numberArgument: %@ stringArgument: %@", numberArgument, stringArgument]]);
}
RCT_EXPORT_METHOD(overlyImages:(NSString *)image1 img:(NSString *)image2 posX:(CGFloat)x posY:(CGFloat)y callback:(RCTResponseSenderBlock)callback)
{
   NSData *data1 = [[NSData alloc]initWithBase64EncodedString:image1 options:NSDataBase64DecodingIgnoreUnknownCharacters];
    UIImage *img1 = [UIImage imageWithData:data1];
   
   NSData *data2 = [[NSData alloc]initWithBase64EncodedString:image2 options:NSDataBase64DecodingIgnoreUnknownCharacters];
    UIImage *img2 = [UIImage imageWithData:data2];

    UIGraphicsBeginImageContextWithOptions(img1.size, FALSE, 1);
    [img1 drawInRect:CGRectMake( 0, 0, img1.size.width, img1.size.height)];
    [img2 drawInRect:CGRectMake( x, y, img2.size.width, img2.size.height)];
    UIImage *newImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
     NSData *data = UIImagePNGRepresentation(newImage);
    NSString *base64Str = [data base64EncodedStringWithOptions:NSDataBase64Encoding64CharacterLineLength];
    callback(@[[NSString stringWithFormat: base64Str]]);
}

@end
