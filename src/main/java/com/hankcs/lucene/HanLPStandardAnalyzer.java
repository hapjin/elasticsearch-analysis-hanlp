package com.hankcs.lucene;

import com.hankcs.cfg.Configuration;
import com.hankcs.hanlp.HanLP;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * 标准分析器
 *
 * @author Kenn
 */
public class HanLPStandardAnalyzer extends Analyzer {
    /**
     * 分词配置
     */
    private Configuration configuration;

    public HanLPStandardAnalyzer(Configuration configuration) {
        this.configuration = configuration;
    }

    public HanLPStandardAnalyzer() {
        super();
    }

    @Override
    protected Analyzer.TokenStreamComponents createComponents(String fieldName) {
        //分词时开启归一化
        AccessController.doPrivileged((PrivilegedAction) () -> HanLP.Config.Normalization = true);
        Tokenizer tokenizer = new HanLPTokenizer(HanLP.newSegment(), configuration);
        return new Analyzer.TokenStreamComponents(tokenizer);
    }
}
