# react-native-images-overly

## Getting started

`$ npm install react-native-images-overly --save`

### Mostly automatic installation

`$ react-native link react-native-images-overly`

## Usage
```javascript
import ImagesOverly from 'react-native-images-overly';

 ImagesOverly.overlyImages(img1, img2, 32, 16, e => {
      console.log(e);
      this.setState({ img: e })
    }
```
where img1, img2 is base64  32, 16 - x,y position