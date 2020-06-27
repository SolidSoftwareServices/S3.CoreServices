using NUnit.Framework;

namespace S3.Testing.NUnit
{

	/// <summary>
	/// Base class for all unit tests... From now on
	/// </summary>
	/// <typeparam name="TContext"></typeparam>
	/// <typeparam name="TSut"></typeparam>
	[TestFixture]
	[Parallelizable(ParallelScope.Fixtures)]
	public abstract class UnitTestFixture<TContext,TSut>
		where TSut : class
		where TContext:UnitTestContext<TSut>, new() 
	{
		protected TContext Context;
		[SetUp]
		public virtual void SetUp()
		{
			Context=new TContext();
		}

		[TearDown]
		public virtual void TearDown()
		{
			Context.Dispose();
		}
	}

}