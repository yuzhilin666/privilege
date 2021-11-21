const gulp = require('gulp');
const autoprefixer = require('autoprefixer');
const cssnano = require('cssnano');
const atImport = require('postcss-import');

gulp.task('postcss', function () {
    var postcss    = require('gulp-postcss');

    return gulp.src('src/02-plugins-main.css')
        .pipe(postcss([
            atImport,
            autoprefixer({
                browsers:['last 2 versions']
            }),
            // cssnano
        ]))
        .pipe(gulp.dest('build/'));
});
