// const autoprefixer = require('autoprefixer');
// const cssnano = require('cssnano');
// const atImport = require('postcss-import');
// const cssnext = require('postcss-cssnext');
const precss = require('precss');
module.exports = {
    plugins: [
        // atImport,
        // cssnext,
        precss
        /* autoprefixer({
            browsers:['Firefox > 1']
        }), */
        // cssnano
    ]
};
